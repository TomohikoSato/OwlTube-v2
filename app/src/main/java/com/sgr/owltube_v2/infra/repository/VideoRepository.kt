package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Thumbnail
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.player.related.RelatedVideos
import com.sgr.owltube_v2.domain.popular.PopularVideos
import com.sgr.owltube_v2.infra.dao.RecentlyWatchedDao
import com.sgr.owltube_v2.infra.webapi.response.channels.ChannelsResponse
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.videolist.VideosResponse
import com.sgr.owltube_v2.infra.webapi.youtube.YoutubeDataAPI
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class VideoRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI, private val recentlyWatchedDao: RecentlyWatchedDao) {

    fun fetchPopularVideos(forceUpdate: Boolean): Single<PopularVideos> {
        val popularVideosResponse = youtubeDataAPI.popularVideos(getCacheControl(forceUpdate), null)
        val channelsResponse = popularVideosResponse.map { videos ->
            videos.items.map { item -> item.snippet.channelId }
                    .joinTo(StringBuilder())
        }.flatMap { ids: StringBuilder -> youtubeDataAPI.channels(getCacheControl(forceUpdate), ids.toString()) }

        //TODO: popularVideos API が二回呼ばれるのをなんとかする
        return Single.zip(popularVideosResponse, channelsResponse, BiFunction { p, c -> createPopularVideos(p, c) })
    }

    fun fetchRelatedVideos(videoId: String): Single<RelatedVideos> {
        return youtubeDataAPI.relatedVideos(videoId)
                .flatMap { response ->
                    val videoIds = response.items.map { item -> item.id.videoId }
                            .joinTo(StringBuilder())
                            .toString()

                    youtubeDataAPI.videos(videoIds)
                }
                .map { videos -> createRelatedVideo(videos) }
    }

    fun fetchRecentlyWatchedVideos(): Single<List<Video>> {
        return recentlyWatchedDao.fetchRecentlyWatched()
                .flatMap { recentlyWatchedVideos ->
                    val videoIds = recentlyWatchedVideos.map { recentlyWatched -> recentlyWatched.videoId }
                            .joinTo(StringBuilder())
                            .toString()
                    youtubeDataAPI.videos(videoIds)
                }.map { videos -> createRecentlyWatchedVideo(videos) }
    }

    private fun createRecentlyWatchedVideo(videosResponse: VideosResponse): List<Video> {
        return videosResponse.items.map { item ->
            Video(item.id,
                    item.snippet.title,
                    Channel(item.snippet.channelId,
                            item.snippet.channelTitle, null),
                    item.statistics.viewCount,
                    Thumbnail(item.snippet.thumbnails.high.url, item.snippet.thumbnails.medium.url),
                    item.snippet.publishedAt,
                    item.contentDetails.duration
            )
        }
    }


    private fun createRelatedVideo(videosResponse: VideosResponse): RelatedVideos {
        val videos = videosResponse.items.map { item ->
            Video(item.id,
                    item.snippet.title,
                    Channel(item.snippet.channelId,
                            item.snippet.channelTitle, null),
                    item.statistics.viewCount,
                    Thumbnail(item.snippet.thumbnails.high.url, item.snippet.thumbnails.medium.url),
                    item.snippet.publishedAt,
                    item.contentDetails.duration
            )
        }

        return RelatedVideos(videos)
    }

    private fun getCacheControl(forceUpdate: Boolean): String? = if (forceUpdate) "no-cache" else null

    private fun createPopularVideos(popularVideoResponse: PopularVideoResponse, channelsResponse: ChannelsResponse): PopularVideos {
        val videos = popularVideoResponse.items.zip(channelsResponse.items)
                .map { pair ->
                    val pItem = pair.first
                    val cItem = pair.second
                    Video(pItem.id,
                            pItem.snippet.title,
                            Channel(pItem.snippet.channelId,
                                    cItem.snippet.title,
                                    cItem.snippet.thumbnails.default.url),
                            pItem.statistics.viewCount,
                            Thumbnail(pItem.snippet.thumbnails.high.url, pItem.snippet.thumbnails.medium.url),
                            pItem.snippet.publishedAt,
                            pItem.contentDetails.duration
                    )
                }
        return PopularVideos(videos)
    }
}
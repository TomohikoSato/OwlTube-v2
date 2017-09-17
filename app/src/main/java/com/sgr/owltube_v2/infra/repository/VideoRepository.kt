package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.VideoImpl
import com.sgr.owltube_v2.domain.player.related.RelatedVideo
import com.sgr.owltube_v2.domain.player.related.RelatedVideos
import com.sgr.owltube_v2.domain.popular.PopularVideos
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.sgr.owltube_v2.infra.webapi.response.channels.ChannelsResponse
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.videolist.VideosResponse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class VideoRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI) {

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

    private fun createRelatedVideo(videosResponse: VideosResponse): RelatedVideos {
        val videos = videosResponse.items.map { item ->
            RelatedVideo(item.id,
                    item.snippet.title,
                    Channel(item.snippet.channelId,
                            item.snippet.channelTitle, null),
                    item.statistics.viewCount,
                    item.snippet.thumbnails.high.url,
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
                    VideoImpl(pItem.id,
                            pItem.snippet.title,
                            Channel(pItem.snippet.channelId,
                                    cItem.snippet.title,
                                    cItem.snippet.thumbnails.default.url),
                            pItem.statistics.viewCount,
                            pItem.snippet.thumbnails.high.url,
                            pItem.snippet.publishedAt,
                            pItem.contentDetails.duration
                    )
                }
        return PopularVideos(videos)
    }
}
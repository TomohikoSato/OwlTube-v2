package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.videos.popular.PopularVideos
import com.sgr.owltube_v2.domain.videos.related.RelatedVideos
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.sgr.owltube_v2.infra.webapi.response.channels.ChannelsResponse
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.search.SearchResponse
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class VideoRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI) {

    fun fetchPopularVideos(forceUpdate: Boolean): Single<PopularVideos> {
        val popularVideoResponse = youtubeDataAPI.popularVideos(getCacheControl(forceUpdate), null)
        val channelResponse = popularVideoResponse.map { videos ->
            videos.items.map { item -> item.snippet.channelId }
                    .joinTo(StringBuilder())
        }.flatMap { ids: StringBuilder -> youtubeDataAPI.channels(getCacheControl(forceUpdate), ids.toString()) }

        //TODO: popularVideos API が二回呼ばれるのをなんとかする
        return Single.zip(popularVideoResponse, channelResponse, BiFunction { p, c -> createPopularVideo(p, c) })
    }

    fun fetchRelatedVideos(videoId: String): Single<RelatedVideos> {
        youtubeDataAPI.relatedVideos(videoId)
                .map { search -> createRelatedVideo(search) }
    }

    private fun createRelatedVideo(search: SearchResponse): RelatedVideos {
        search.items.map { item ->
            Video(item.id,
                    item.snippet.title,
                    Channel(item.snippet.channelId,
                            item.snippet.channelTitle,
                            item.statistics.viewCount,
                            item.snippet.thumbnails.high.url,
                            item.snippet.publishedAt,
                            item.contentDetails.duration
                    )
        }

        TODO()
    }

    private fun getCacheControl(forceUpdate: Boolean): String? = if (forceUpdate) "no-cache" else null

    private fun createPopularVideo(popularVideoResponse: PopularVideoResponse, channelsResponse: ChannelsResponse): PopularVideos {
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
                            pItem.snippet.thumbnails.high.url,
                            pItem.snippet.publishedAt,
                            pItem.contentDetails.duration
                    )
                }
        return PopularVideos(videos)
    }
}
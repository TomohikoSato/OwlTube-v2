package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.sgr.owltube_v2.infra.webapi.response.channels.Channels
import com.sgr.owltube_v2.infra.webapi.response.popular.Item
import com.sgr.owltube_v2.infra.webapi.response.popular.Popular
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class VideoRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI) {

    fun fetchPopular(): Single<List<Video>> {
        val popular = youtubeDataAPI.popular(null)
        val channels = popular.map { p ->
            p.items.map { item -> item.snippet.channelId }
                    .joinTo(StringBuilder())
        }.flatMap { ids -> youtubeDataAPI.channels(ids.toString()) }

        return popular.zipWith(channels, BiFunction { p: Popular, c: Channels -> createVideo(p, c) })
    }

    private fun createVideo(popular: Popular, channels: Channels): List<Video> {
        return popular.items.zip(channels.items)
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
    }
}
package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Thumbnail
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.sgr.owltube_v2.infra.webapi.response.videolist.VideosResponse
import io.reactivex.Single
import javax.inject.Inject

class SearchRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI) {
    // TODO: suggest
    // TODO: search history


    fun search(query: String): Single<List<Video>> {
        return youtubeDataAPI.search(query)
                .flatMap { searchResultResponse ->
                    val videoIds = searchResultResponse.items.map { item -> item.id.videoId }
                            .joinTo(StringBuilder())
                            .toString()

                    youtubeDataAPI.videos(videoIds)
                }
                .map { videos -> createVideos(videos) }
    }

    private fun createVideos(videosResponse: VideosResponse): List<Video> {
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

        return videos
    }

}
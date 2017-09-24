package com.sgr.owltube_v2.infra.repository

import android.util.Log
import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Thumbnail
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.search.Suggest
import com.sgr.owltube_v2.infra.webapi.google.GoogleAPI
import com.sgr.owltube_v2.infra.webapi.response.videolist.VideosResponse
import com.sgr.owltube_v2.infra.webapi.youtube.YoutubeDataAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepository @Inject constructor(private val youtubeDataAPI: YoutubeDataAPI, private val googleAPI: GoogleAPI) {
    fun suggest(query: String): Single<List<Suggest>> {
        googleAPI.suggestKeywordForYoutube(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> Log.d("hoge", result.toString()) })

        return Single.fromCallable { listOf(Suggest("hoge")) }
    }

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
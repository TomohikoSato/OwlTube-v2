package com.sgr.owltube_v2.infra.webapi

import com.sgr.owltube_v2.BuildConfig
import com.sgr.owltube_v2.infra.webapi.response.channels.Channels
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeDataAPI {

    @GET("videos?part=snippet,statistics,contentDetails&chart=mostPopular&regionCode=JP&maxResults=" + MAX_RESULTS + "&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun popularVideos(@Query("pageToken") pageToken: String?): Single<PopularVideo>

    @GET("channels?part=snippet&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun channels(@Query("id") id: String): Single<Channels>

/*
    @GET("search?part=snippet&regionCode=JP&type=video&maxResults=" + MAX_RESULTS + "&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun search(@Query("q") q: String, @Query("pageToken") pageToken: String?): Single<Search>

    @GET("videos?part=statistics&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun videoListStatistics(@Query("id") videoIds: String): Single<VideoList>

*/

/*

    @GET("search?part=snippet&maxResults=10&type=video&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun relatedToVideoId(@Query("relatedToVideoId") id: String): Single<Search>
*/

    companion object {
        const val MAX_RESULTS = 50
    }
}
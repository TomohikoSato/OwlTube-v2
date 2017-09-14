package com.sgr.owltube_v2.infra.webapi

import com.sgr.owltube_v2.BuildConfig
import com.sgr.owltube_v2.infra.webapi.response.channels.ChannelsResponse
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.search.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YoutubeDataAPI {

    @GET("videos?part=snippet,statistics,contentDetails&chart=mostPopular&regionCode=JP&maxResults=50&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun popularVideos(@Header("Cache-Control") cacheControl: String?, @Query("pageToken") pageToken: String?): Single<PopularVideoResponse>

    @GET("channels?part=snippet&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun channels(@Header("Cache-Control") cacheControl: String?, @Query("id") id: String): Single<ChannelsResponse>

    @GET("search?part=snippet&maxResults=10&type=video&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun relatedVideos(@Query("relatedToVideoId") videoId: String): Single<SearchResponse>


/*
    @GET("search?part=snippet&regionCode=JP&type=video&maxResults=" + MAX_RESULTS + "&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun search(@Query("q") q: String, @Query("pageToken") pageToken: String?): Single<SearchResponse>

    @GET("videos?part=statistics&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun videoListStatistics(@Query("id") videoIds: String): Single<VideoList>

*/
}
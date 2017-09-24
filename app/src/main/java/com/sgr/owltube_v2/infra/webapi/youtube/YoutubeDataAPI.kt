package com.sgr.owltube_v2.infra.webapi.youtube

import com.sgr.owltube_v2.BuildConfig
import com.sgr.owltube_v2.infra.webapi.response.channels.ChannelsResponse
import com.sgr.owltube_v2.infra.webapi.response.popular.PopularVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.search.RelatedVideoResponse
import com.sgr.owltube_v2.infra.webapi.response.search.SearchResultResponse
import com.sgr.owltube_v2.infra.webapi.response.videolist.VideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YoutubeDataAPI {

    @GET("videos?part=snippet,statistics,contentDetails&chart=mostPopular&regionCode=JP&maxResults=50&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun popularVideos(@Header("Cache-Control") cacheControl: String?, @Query("pageToken") pageToken: String?): Single<PopularVideoResponse>

    /**
     * @param videoIds comma-separated list of YouTube video IDs
     */
    @GET("videos?part=snippet,statistics,contentDetails&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun videos(@Query("id") videoIds: String): Single<VideosResponse>

    /**
     * @param channelIds comma-separated list of YouTube channel IDs
     */
    @GET("channels?part=snippet&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun channels(@Header("Cache-Control") cacheControl: String?, @Query("id") channelIds: String): Single<ChannelsResponse>

    @GET("search?part=snippet&maxResults=10&type=video&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun relatedVideos(@Query("relatedToVideoId") videoId: String): Single<RelatedVideoResponse>

    @GET("search?part=snippet&type=video&maxResults=50&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun search(@Query("q") q: String): Single<SearchResultResponse>

/*
    @GET("videos?part=statistics&key=" + BuildConfig.YOUTUBE_DATA_API_KEY)
    fun videoListStatistics(@Query("id") videoIds: String): Single<VideoList>

*/
}
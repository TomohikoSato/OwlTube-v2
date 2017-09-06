package com.sgr.owltube_v2.infra.repository

import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.webapi.YoutubeDataAPI
import com.sgr.owltube_v2.infra.webapi.response.popular.Popular
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class VideoRepository @Inject constructor (private val youtubeDataAPI: YoutubeDataAPI){

    fun fetchPopular(): Single<List<Video>> {
        return youtubeDataAPI.popular(null)
                .map { p: Popular -> p.toVideos() }
        }
}
package com.sgr.owltube_v2.frontend.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(private val videoRepository: VideoRepository) {

    val searchResultVideos: ObservableList<Video> = ObservableArrayList<Video>()

    fun search(query: String) {
        videoRepository.search(query)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { videos -> searchResultVideos.addAll(videos) }
    }
}
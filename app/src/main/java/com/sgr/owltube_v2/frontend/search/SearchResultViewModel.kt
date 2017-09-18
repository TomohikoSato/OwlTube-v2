package com.sgr.owltube_v2.frontend.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(private val videoRepository: VideoRepository) {

    val searchResultVideos: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun search(query: String) {
        videoRepository.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { videos -> searchResultVideos.addAll(videos) }
    }
}
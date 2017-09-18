package com.sgr.owltube_v2.frontend.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    val searchResultVideos: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()
    val isRefreshing: ObservableBoolean = ObservableBoolean(false)

    fun search(query: String) {
        isRefreshing.set(true)
        videoRepository.search(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    isRefreshing.set(false)
                    searchResultVideos.addAll(videos)
                }, { t -> t.printStackTrace() })
    }
}
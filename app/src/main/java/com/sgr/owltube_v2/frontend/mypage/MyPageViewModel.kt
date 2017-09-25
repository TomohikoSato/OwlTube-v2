package com.sgr.owltube_v2.frontend.mypage

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyPageViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    val items: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun fetchRecentlyWatchedVideos() {
        items.clear()
        items.add(0, MyPageAdapter.HeaderItem())
        videoRepository.fetchRecentlyWatchedVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos -> items.addAll(videos) })
    }
}
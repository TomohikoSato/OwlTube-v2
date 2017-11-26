package com.sgr.owltube_v2.frontend.mypage

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.frontend.common.RequestStatus
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MyPageViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    val items = ObservableArrayList<AdapterItem>()
    val status = ObservableField<RequestStatus>(RequestStatus.INIT)

    fun fetchRecentlyWatchedVideos() {
        init()
        status.set(RequestStatus.REQUESTING)
        videoRepository.fetchRecentlyWatchedVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    status.set(RequestStatus.SUCCESS)
                    items.addAll(videos)
                }, { t ->
                    status.set(RequestStatus.ERROR)
                    Logger.w(t)
                })
    }

    fun clearAllRecentlyWatched() =
            videoRepository.clearAllRecentlyWatched().subscribe { init() }

    private fun init() = items.apply {
        clear()
        add(0, MyPageAdapter.HeaderItem())
    }
}
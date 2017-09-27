package com.sgr.owltube_v2.frontend.top

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableList
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    enum class RequestStatus {
        INIT,
        REQUESTING,
        SUCCESS,
        ERROR;

        fun isRequesting(): Boolean = (this == REQUESTING)
        fun isSuccess(): Boolean = (this == SUCCESS)
        fun isError(): Boolean = (this == ERROR)
    }

    val videos: ObservableList<Video> = ObservableArrayList<Video>()

    val status: ObservableField<RequestStatus> = ObservableField<RequestStatus>(RequestStatus.INIT)

    fun requestPopularVideos() = requestPopularVideos(false)

    fun refreshPopularVideo() = requestPopularVideos(true)

    private fun requestPopularVideos(forceUpdate: Boolean) {
        status.set(RequestStatus.REQUESTING)
        videoRepository.fetchPopularVideos(forceUpdate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    status.set(RequestStatus.SUCCESS)
                    this.videos.clear()
                    this.videos.addAll(videos.videos)
                }, { t ->
                    status.set(RequestStatus.ERROR)
                    Logger.w(t)
                })
    }
}
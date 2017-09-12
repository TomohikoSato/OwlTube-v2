package com.sgr.owltube_v2.frontend.top

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.util.Log
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopItemViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    val videos: ObservableList<Video> = ObservableArrayList<Video>()
    val isRefreshing: ObservableBoolean = ObservableBoolean(false)

    fun requestPopularVideos() {
        isRefreshing.set(true)
        videoRepository.fetchPopularVideos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    isRefreshing.set(false)
                    this.videos.clear()
                    this.videos.addAll(videos)
                }, { t -> Log.e("TopItemViewMoedel", t.toString()) })
    }

    fun refreshPopularVideo() = requestPopularVideos()
}
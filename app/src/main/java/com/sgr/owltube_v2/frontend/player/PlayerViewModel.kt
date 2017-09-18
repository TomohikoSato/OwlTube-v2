package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val videoRepository: VideoRepository) {

    val playerItem: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun requestRelatedVideos(videoId: String) {
        videoRepository.fetchRelatedVideos(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relatedVideos -> this.playerItem.addAll(relatedVideos.videos) }
    }
}
package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val videoRepository: VideoRepository) {

//    val relatedVideos: ObservableField<RelatedVideos> = ObservableField<RelatedVideos>()
//    val relatedVideos: ObservableField<RelatedVideos> = ObservableField<RelatedVideos>()

    val playerItem: ObservableList<PlayerAdapterItem> = ObservableArrayList<PlayerAdapterItem>()

    fun requestRelatedVideos(videoId: String) {
        videoRepository.fetchRelatedVideos(videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relatedVideos -> this.playerItem.addAll(relatedVideos.videos) }
    }
}
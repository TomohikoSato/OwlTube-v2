package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableField
import com.sgr.owltube_v2.domain.related.RelatedVideos
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val videoRepository: VideoRepository) {

    val relatedVideos: ObservableField<RelatedVideos> = ObservableField<RelatedVideos>()

    fun requestRelatedVideos(videoId: String) {
        videoRepository.fetchRelatedVideos(videoId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relatedVideos -> this.relatedVideos.set(relatedVideos) }
    }
}
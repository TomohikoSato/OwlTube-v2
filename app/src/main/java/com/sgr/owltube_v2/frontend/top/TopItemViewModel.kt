package com.sgr.owltube_v2.frontend.top

import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TopItemViewModel @Inject constructor (private val videoRepository: VideoRepository) {
    var videos: List<Video> = listOf<Video>()

    fun requestItems() {
        videoRepository.fetchPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{vs -> this.videos = vs}
    }
}
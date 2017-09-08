package com.sgr.owltube_v2.frontend.top

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class TopItemViewModel @Inject constructor(private val videoRepository: VideoRepository) {
    var videos: ObservableList<Video> = ObservableArrayList<Video>()

    fun requestItems() {
        videoRepository.fetchPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ videos ->
                    this.videos.clear()
                    this.videos.addAll(videos)
                }, { t -> Log.e("TopItemViewMoedel", t.toString()) })
    }
}
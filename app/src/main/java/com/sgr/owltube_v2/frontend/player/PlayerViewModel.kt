package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.dao.RecentlyWatched
import com.sgr.owltube_v2.infra.dao.RecentlyWatchedDao
import com.sgr.owltube_v2.infra.repository.VideoRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val videoRepository: VideoRepository,
                                          private val recentlyWatchedDao: RecentlyWatchedDao) {

    val playerItem: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun requestRelatedVideos(videoId: String) {
        videoRepository.fetchRelatedVideos(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { relatedVideos -> this.playerItem.addAll(relatedVideos.videos) }
    }

    fun addRecentlyWatched(video: Video) {
        Completable.fromAction { recentlyWatchedDao.addRecentlyWatched(RecentlyWatched(video.id)) }
                .subscribeOn(Schedulers.io())
                .subscribe {}
    }
}
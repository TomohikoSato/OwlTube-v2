package com.sgr.owltube_v2.frontend.player

import com.sgr.owltube_v2.domain.Video
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*

class PlayedVideos {
    private val videos: Stack<Video> = Stack()

    val changedListener: PublishSubject<Stack<Video>> = PublishSubject.create<Stack<Video>>()

    fun push(video: Video) {
        videos.push(video)
        changedListener.onNext(videos)
    }

    fun pop(): Video {
        val poped = videos.pop()
        changedListener.onNext(videos)
        return poped
    }

    fun clear() {
        videos.clear()
        changedListener.onNext(videos)
    }

    fun empty(): Boolean = videos.empty()
}
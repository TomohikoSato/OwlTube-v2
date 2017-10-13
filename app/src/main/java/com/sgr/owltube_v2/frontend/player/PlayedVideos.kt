package com.sgr.owltube_v2.frontend.player

import com.sgr.owltube_v2.domain.Video
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

    fun peek(): Video = videos.peek()

    fun clear() {
        videos.clear()
        changedListener.onNext(videos)
    }

    fun refresh() {
        changedListener.onNext(videos)
    }

    fun empty(): Boolean = videos.empty()
}
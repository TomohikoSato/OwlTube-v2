package com.sgr.owltube_v2.frontend.player

import com.sgr.owltube_v2.domain.Video
import io.reactivex.subjects.PublishSubject
import java.util.*

class PlayedVideos {
    enum class Void {
        INSTANCE
    }

    private var stack: Stack<Video> = Stack()

    val changedListener: PublishSubject<Stack<Video>> = PublishSubject.create()

    fun push(video: Video) {
        stack.push(video)
        changedListener.onNext(stack)
    }

    fun pop(): Video {
        val poped = stack.pop()
        changedListener.onNext(stack)
        return poped
    }

    fun empty(): Boolean = stack.empty()

    fun clear() {
        stack.clear()
        changedListener.onNext(stack)
    }

    fun count(): Int = stack.count()
}
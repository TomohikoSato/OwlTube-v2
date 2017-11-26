package com.sgr.owltube_v2.common.ext

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.Disposable

/**
 * ライフサイクルの適当なタイミングで[Disposable.dispose]するクラス
 */
class Disposer(lifecycle: Lifecycle,
               private val disposeTiming: Lifecycle.Event = Lifecycle.Event.ON_DESTROY) : LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private val disposables: MutableList<Disposable> = mutableListOf()

    fun add(disposable: Disposable) = disposables.add(disposable)

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun disposeIfTiming(owner: LifecycleOwner, timing: Lifecycle.Event) {
        if (timing == disposeTiming) {
            disposables.forEach { it.dispose() }
            disposables.clear()
        }
    }
}
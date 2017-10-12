package com.sgr.owltube_v2.frontend.player

enum class PreviousVideoState(val level: Int) {
    NONE(0) {
        override fun toggle(): PreviousVideoState = HAS
    },
    HAS(1) {
        override fun toggle(): PreviousVideoState = NONE
    };

    companion object {
        fun of(value: Int): RepeatState {
            RepeatState.values().forEach {
                if (it.level == value) return it
            }
            throw IllegalArgumentException()
        }
    }

    abstract fun toggle(): PreviousVideoState
}
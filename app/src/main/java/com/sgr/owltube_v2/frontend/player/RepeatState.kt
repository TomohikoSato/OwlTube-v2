package com.sgr.owltube_v2.frontend.player

enum class RepeatState(val level: Int) {
    OFF(0) {
        override fun next(): RepeatState = ON_ONE
    },
    ON_ONE(1) {
        override fun next(): RepeatState = OFF
    };

    companion object {
        fun of(value: Int): RepeatState {
            values().forEach {
                if (it.level == value) return it
            }
            throw IllegalArgumentException()
        }
    }

    abstract fun next(): RepeatState
}
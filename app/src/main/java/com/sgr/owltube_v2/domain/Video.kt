package com.sgr.owltube_v2.domain

import org.threeten.bp.Duration


data class Video(val id: String,
                 val title: String,
                 val channel: Channel,
                 val viewCount: String,
                 val thumbnailUrl: String,
                 val publishedAt: String,
                 val _duration: String) {

    val duration: String
        get() {
            val d = Duration.parse(_duration)
            return if (d.compareTo(Duration.ofHours(1)) >= 0)
                String.format("%d:%02d:%02d", d.toHours(), d.toMinutes() % 60, d.seconds % 60) else
                String.format("%02d:%02d", d.toMinutes() % 60, d.seconds % 60)
        }
}
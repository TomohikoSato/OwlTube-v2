package com.sgr.owltube_v2.domain

data class Video(val videoId: String,
                 val title: String,
                 val channelTitle: String,
                 val viewCount: String,
                 val thumbnailUrl: String) {
    fun fromResponse() {

    }
}


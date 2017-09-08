package com.sgr.owltube_v2.domain

data class Video(val id: String,
                 val title: String,
                 val channel: Channel,
                 val viewCount: String,
                 val thumbnailUrl: String,
                 val publishedAt: String,
                 val duration: String)
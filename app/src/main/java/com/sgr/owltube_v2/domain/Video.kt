package com.sgr.owltube_v2.domain

data class Video(val id: String,
                 val title: String,
                 val channelTitle: String,
                 val channelId: String,
                 val channelThumbnailUrl: String,
                 val viewCount: String,
                 val thumbnailUrl: String,
                 val publishedAt: String,
                 val duration: String)
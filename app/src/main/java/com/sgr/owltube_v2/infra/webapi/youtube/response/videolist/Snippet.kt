package com.sgr.owltube_v2.infra.webapi.youtube.response.videolist

data class Snippet(val publishedAt: String,
                   val channelId: String,
                   val title: String,
                   val description: String,
                   val thumbnails: Thumbnails,
                   val channelTitle: String,
                   val categoryId: String,
                   val liveBroadcastContent: String,
                   val localized: Localized)
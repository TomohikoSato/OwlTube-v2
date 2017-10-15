package com.sgr.owltube_v2.infra.webapi.youtube.response.search

data class Snippet(val publishedAt: String,
                   val channelId: String,
                   val title: String,
                   val description: String,
                   val thumbnails: Thumbnails,
                   val channelTitle: String,
                   val liveBroadcastContent: String)
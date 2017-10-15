package com.sgr.owltube_v2.infra.webapi.youtube.response.channels

data class Snippet(val title: String,
                   val description: String,
                   val publishedAt: String,
                   val thumbnails: Thumbnails,
                   val localized: Localized)
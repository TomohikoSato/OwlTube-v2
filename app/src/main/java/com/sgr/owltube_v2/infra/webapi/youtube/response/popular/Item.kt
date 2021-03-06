package com.sgr.owltube_v2.infra.webapi.youtube.response.popular

data class Item(val kind: String,
                val etag: String,
                val id: String,
                val snippet: Snippet,
                val statistics: Statistics,
                val contentDetails: ContentDetails)
package com.sgr.owltube_v2.infra.webapi.youtube.response.search

data class Item(val kind: String,
                val etag: String,
                val id: Id,
                val snippet: Snippet)

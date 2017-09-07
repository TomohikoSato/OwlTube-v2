package com.sgr.owltube_v2.infra.webapi.response.popular

import com.sgr.owltube_v2.domain.Video

data class Popular(val kind: String, val etag: String, val nextPageToken: String, val pageInfo: PageInfo, val items: List<Item>) {
    fun toVideos(): List<Video> = items.map { item ->
        Video(item.id, item.snippet.title, item.snippet.channelTitle, item.statistics.viewCount, item.snippet.thumbnails.high.url, item.snippet.publishedAt)
    }
}
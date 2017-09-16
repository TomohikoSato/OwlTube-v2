package com.sgr.owltube_v2.domain.player.related

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.VideoImpl
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

data class RelatedVideo(override val id: String,
                        override val title: String,
                        override val channel: Channel,
                        override val viewCount: String,
                        override val thumbnailUrl: String,
                        override val publishedAt: String,
                        override val duration: String) : Video
by VideoImpl(id,
        title,
        channel,
        viewCount,
        thumbnailUrl,
        publishedAt,
        duration), PlayerAdapterItem {

    constructor(video: Video) : this(video.id,
            video.title,
            video.channel,
            video.viewCount,
            video.thumbnailUrl,
            video.publishedAt,
            video.duration)
}
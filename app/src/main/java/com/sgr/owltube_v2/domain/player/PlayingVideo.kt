package com.sgr.owltube_v2.domain.player

import com.sgr.owltube_v2.domain.Channel
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.VideoImpl
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

/**
 * Playerで再生している動画の情報
 */
class PlayingVideo(override val id: String,
                   override val title: String,
                   override val channel: Channel,
                   override val _viewCount: String,
                   override val thumbnailUrl: String,
                   override val _publishedAt: String,
                   override val _duration: String) : Video
by VideoImpl(id,
        title,
        channel,
        _viewCount,
        thumbnailUrl,
        _publishedAt,
        _duration), PlayerAdapterItem {

    constructor(video: Video) : this(video.id,
            video.title,
            video.channel,
            video._viewCount,
            video.thumbnailUrl,
            video._publishedAt,
            video._duration)
}

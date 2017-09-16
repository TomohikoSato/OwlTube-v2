package com.sgr.owltube_v2.domain.related

import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

data class RelatedVideos(val videos: List<Video>) : PlayerAdapterItem
package com.sgr.owltube_v2.domain

import java.io.Serializable

/**
 * @link https://developers.google.com/youtube/v3/docs/thumbnails
 */
data class Thumbnail(
        /** 480px wide and 360px tall */
        val high: String,

        /** 320px wide and 180px tall. */
        val medium: String

        /** default 120px wide and 90px tall */
) : Serializable

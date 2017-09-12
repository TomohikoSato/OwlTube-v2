package com.sgr.owltube_v2.domain

import java.io.Serializable

data class Channel(
        val id: String,
        val title: String,
        val thumbnailUrl: String) : Serializable
package com.sgr.owltube_v2.infra.webapi.youtube.response.channels

data class Channels(val kind: String,
                    val etag: String,
                    val pageInfo: PageInfo,
                    val items: List<Item>)
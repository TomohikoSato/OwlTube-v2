package com.sgr.owltube_v2.infra.webapi.youtube.response.videolist

data class VideosResponse(val kind: String,
                          val etag: String,
                          val pageInfo: PageInfo,
                          val items: List<Item>)
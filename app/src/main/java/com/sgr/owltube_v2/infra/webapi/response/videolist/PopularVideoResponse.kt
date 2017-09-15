package com.sgr.owltube_v2.infra.webapi.response.videolist

data class VideosResponse(val kind: String,
                          val etag: String,
                          val nextPageToken: String,
                          val pageInfo: PageInfo,
                          val items: List<Item>)
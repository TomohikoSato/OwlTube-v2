package com.sgr.owltube_v2.infra.webapi.response.popular

data class PopularVideoResponse(val kind: String,
                                val etag: String,
                                val nextPageToken: String,
                                val pageInfo: PageInfo,
                                val items: List<Item>)
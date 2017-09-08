package com.sgr.owltube_v2.infra.webapi.response.popular

import com.sgr.owltube_v2.domain.Video

data class Popular(val kind: String, val etag: String, val nextPageToken: String, val pageInfo: PageInfo, val items: List<Item>)
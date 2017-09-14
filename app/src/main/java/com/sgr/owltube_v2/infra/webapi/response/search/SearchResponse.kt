package com.sgr.owltube_v2.infra.webapi.response.search

data class SearchResponse(val kind: String,
                          val etag: String,
                          val nextPageToken: String,
                          val regionCode: String,
                          val pageInfo: PageInfo,
                          val items: List<Item>)
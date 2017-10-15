package com.sgr.owltube_v2.infra.webapi.youtube.response.videolist

data class ContentDetails(val dimension: String,
                          val duration: String,
                          val licensedContent: Boolean,
                          val definition: String,
                          val projection: String,
                          val caption: String)

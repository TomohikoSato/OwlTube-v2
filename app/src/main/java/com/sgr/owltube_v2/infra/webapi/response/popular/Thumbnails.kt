package com.sgr.owltube_v2.infra.webapi.response.popular

import com.sgr.owltube_v2.infra.webapi.response.channels.Default
import com.sgr.owltube_v2.infra.webapi.response.channels.High
import com.sgr.owltube_v2.infra.webapi.response.channels.Medium

data class Thumbnails(val default: Default, val medium: Medium, val high: High)

package com.sgr.owltube_v2.infra.webapi.google.response

data class SuggestKeywordResponse(val response: Map<Keyword, List<Suggest>>)

data class Keyword(val keyword: String)

data class Suggest(val suggest: String)
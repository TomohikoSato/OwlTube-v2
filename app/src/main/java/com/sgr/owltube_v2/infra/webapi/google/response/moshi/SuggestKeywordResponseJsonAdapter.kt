package com.sgr.owltube_v2.infra.webapi.google.response.moshi

import android.util.Log
import com.sgr.owltube_v2.infra.webapi.google.response.Keyword
import com.sgr.owltube_v2.infra.webapi.google.response.Suggest
import com.sgr.owltube_v2.infra.webapi.google.response.SuggestKeywordResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class SuggestKeywordResponseJsonAdapter {
    @FromJson
    fun fromJson(fromJson: String): SuggestKeywordResponse {
        Log.d("fromJson", fromJson)
        return SuggestKeywordResponse(mapOf(Pair(Keyword("hoge"), listOf<Suggest>(Suggest("fuga")))))
    }

    @ToJson
    fun toJson(response: SuggestKeywordResponse): String {
        TODO("no use")
    }
}
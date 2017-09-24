package com.sgr.owltube_v2.infra.webapi.google.response.moshi

import android.util.Log
import com.sgr.owltube_v2.infra.webapi.google.response.CompleteSuggestion
import com.sgr.owltube_v2.infra.webapi.google.response.SuggestKeywordResponse
import com.sgr.owltube_v2.infra.webapi.google.response.Suggestion
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class SuggestKeywordResponseJsonAdapter {
    @FromJson
    fun fromJson(fromJson: String): SuggestKeywordResponse {
        Log.d("fromJson", fromJson)
        return SuggestKeywordResponse().apply { suggests = listOf(CompleteSuggestion().apply { suggestion = Suggestion().apply { data = "hoge" } }) }//SuggestKeywordResponse(mapOf(Pair(Keyword("hoge"), listOf<Suggest>(Suggest("fuga")))))
    }

    @ToJson
    fun toJson(response: SuggestKeywordResponse): String {
        TODO("no use")
    }
}
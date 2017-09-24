package com.sgr.owltube_v2.infra.webapi.google

import com.sgr.owltube_v2.infra.webapi.google.response.SuggestKeywordResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleAPI {
    /**
     * fetch suggest keyword for youtube
     *
     * 非公開APIなので無断で変更される可能性あり
     * clientがfirefoxな理由：firefox向けのレスポンスが簡潔で扱いやすかったから
     * ds=yt でyoutube向けのsuggestにしている
     */
    @GET("search?client=toolbar&ds=yt")
    fun suggestKeywordForYoutube(@Query("q") q: String): Single<SuggestKeywordResponse>
}

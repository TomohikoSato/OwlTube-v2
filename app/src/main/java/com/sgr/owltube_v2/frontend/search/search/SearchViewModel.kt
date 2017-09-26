package com.sgr.owltube_v2.frontend.search.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.domain.search.Suggest
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.SearchHistoryRepository
import com.sgr.owltube_v2.infra.repository.SearchRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: SearchRepository,
                                          private val searchHistoryRepository: SearchHistoryRepository) {

    val items: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun fetchSuggestKeyword(query: String) {
        items.clear()
        if (query.isEmpty()) return

        Single.zip(searchHistoryRepository.fetchSearchHistoriesMatchesQuery(query),
                repository.suggest(query),
                BiFunction { histories: List<SearchHistory>, suggests: List<Suggest> ->
                    histories.plus(suggests)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ results: List<AdapterItem> -> items.addAll(results) }, Logger::e)
    }
}
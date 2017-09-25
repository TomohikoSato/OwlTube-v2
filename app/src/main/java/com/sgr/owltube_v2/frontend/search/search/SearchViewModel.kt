package com.sgr.owltube_v2.frontend.search.search

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.frontend.search.history.SearchHistoryViewModel
import com.sgr.owltube_v2.infra.repository.SearchHistoryRepository
import com.sgr.owltube_v2.infra.repository.SearchRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val repository: SearchRepository,
                                          private val searchHistoryRepository: SearchHistoryRepository) {

    val items: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun fetchSuggestKeyword(query: String) {
        items.clear()
        if (query.isEmpty()) return

        searchHistoryRepository
                .fetchSearchHistories(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchHistories -> items.addAll(searchHistories) },
                        { t -> Log.e(SearchHistoryViewModel.TAG, t.message) })

        repository.suggest(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ suggests -> items.addAll(suggests) }, { t -> Log.e(TAG, t.message) })
    }

    companion object {
        val TAG: String = SearchViewModel::class.java.simpleName
    }
}
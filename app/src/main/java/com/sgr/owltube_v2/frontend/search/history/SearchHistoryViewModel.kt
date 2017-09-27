package com.sgr.owltube_v2.frontend.search.history

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.SearchHistoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchHistoryViewModel @Inject constructor(
        private val searchHistoryRepository: SearchHistoryRepository) {

    val searchHistories: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun fetchSearchHistories() {
        searchHistories.clear()
        searchHistoryRepository
                .fetchSearchHistories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchHistories -> this.searchHistories.addAll(searchHistories) },
                        Logger::w)
    }
}
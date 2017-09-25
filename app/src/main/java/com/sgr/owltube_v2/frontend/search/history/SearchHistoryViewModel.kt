package com.sgr.owltube_v2.frontend.search.history

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.infra.repository.SearchHistoryRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchHistoryViewModel @Inject constructor(
        private val searchHistoryRepository: SearchHistoryRepository) {

    val searchHistories: ObservableList<AdapterItem> = ObservableArrayList<AdapterItem>()

    fun fetchSearchHistories() {
        searchHistoryRepository
                .fetchSearchHistories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ searchHistories -> this.searchHistories.addAll(searchHistories) },
                        { t -> Log.e(TAG, t.message) })
    }

    private fun getKeyword(position: Int) = (searchHistories.get(position) as SearchHistory).keyword

    companion object {
        val TAG: String = SearchHistoryViewModel::class.java.simpleName
    }
}
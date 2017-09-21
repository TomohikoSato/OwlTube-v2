package com.sgr.owltube_v2.infra.repository

import android.content.Context
import com.sgr.owltube_v2.domain.search.SearchHistory
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(val context: Context) {

    fun fetchSearchHistory(): List<SearchHistory> {
        TODO()
    }

    fun addSearchHistory(searchHistory: SearchHistory) {
        TODO()
    }

    fun clear(searchHistory: SearchHistory) {
        TODO()
    }

    fun clearAll() {
        TODO()
    }
}
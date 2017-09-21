package com.sgr.owltube_v2.infra.repository

import android.content.Context
import android.content.SearchRecentSuggestionsProvider
import android.net.Uri
import android.provider.SearchRecentSuggestions
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.search.SearchHistory
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(val suggestions: SearchRecentSuggestions, val provider: SearchRecentSuggestionsProvider, val context: Context) {

    fun fetchSearchHistory(): List<SearchHistory> {
        val uri = Uri.parse("content://" + (context.getString(R.string.search_history_provider_authority)))

        provider.query(uri,
                SearchRecentSuggestions.QUERIES_PROJECTION_1LINE,
                )
    }

    fun addSearchHistory(searchHistory: SearchHistory) {
        suggestions.saveRecentQuery(searchHistory.value, null)
    }

    fun clear(searchHistory: SearchHistory) {
        TODO()
    }

    fun clearAll() {
        suggestions.clearHistory()
    }
}
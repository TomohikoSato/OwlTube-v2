package com.sgr.owltube_v2.infra.content_provider

import android.content.SearchRecentSuggestionsProvider
import com.sgr.owltube_v2.R

class SearchHistoryProvider : SearchRecentSuggestionsProvider() {

    companion object {
        const val MODE = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }

    init {
        setupSuggestions(context.getString(R.string.search_history_provider_authority), MODE);
    }

}
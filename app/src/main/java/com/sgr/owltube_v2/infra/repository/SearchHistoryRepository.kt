package com.sgr.owltube_v2.infra.repository

import android.content.Context
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.infra.dao.db.AppDatabase
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SearchHistoryRepository @Inject constructor(val appDatabase: AppDatabase, val context: Context) {

    fun fetchSearchHistories(): Single<List<SearchHistory>> {
        return appDatabase.searchHistoryDao().fetchSearchHistories()
    }

    fun fetchSearchHistoriesMatchesQuery(query: String): Single<List<SearchHistory>> {
        return appDatabase.searchHistoryDao()
                .fetchSearchHistories()
                .map { searchHistories ->
                    searchHistories
                            .filter { searchHistory ->
                                searchHistory.keyword.contains(query)
                            }
                }
    }

    fun addSearchHistory(searchHistory: SearchHistory): Completable {
        return Completable.fromAction {
            appDatabase.searchHistoryDao().addSearchHistory(searchHistory)
        }
    }

    fun clear(searchHistory: SearchHistory) {
        TODO()
    }

    fun clearAll() {
        TODO()
    }
}
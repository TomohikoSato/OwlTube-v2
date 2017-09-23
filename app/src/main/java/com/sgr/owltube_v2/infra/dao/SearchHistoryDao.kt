package com.sgr.owltube_v2.infra.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.sgr.owltube_v2.domain.search.SearchHistory
import io.reactivex.Single

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM SearchHistory")
    fun fetchSearchHistories(): Single<List<SearchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchHistory(searchHistory: SearchHistory)

/*    fun clear(searchHistory: SearchHistory)

    fun clearAll()*/
}
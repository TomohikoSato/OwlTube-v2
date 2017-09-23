package com.sgr.owltube_v2.infra.dao.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.infra.dao.SearchHistoryDao

@Database(entities = arrayOf(SearchHistory::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
}
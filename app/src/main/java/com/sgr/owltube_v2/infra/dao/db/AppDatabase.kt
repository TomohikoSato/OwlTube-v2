package com.sgr.owltube_v2.infra.dao.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.infra.dao.RecentlyWatched
import com.sgr.owltube_v2.infra.dao.RecentlyWatchedDao
import com.sgr.owltube_v2.infra.dao.SearchHistoryDao


@Database(entities = arrayOf(SearchHistory::class, RecentlyWatched::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

    abstract fun recentlyWatchedDao(): RecentlyWatchedDao

/*    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `RecnetlyWatched` (`videoId` TEXT PRIMARY KEY)")
            }
        }
    }*/
}
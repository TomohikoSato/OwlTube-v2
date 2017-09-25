package com.sgr.owltube_v2.infra.dao

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface RecentlyWatchedDao {

    @Query("SELECT * FROM RecentlyWatched ORDER BY rowid DESC")
    fun fetchRecentlyWatched(): Single<List<RecentlyWatched>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecentlyWatched(recentlyWatched: RecentlyWatched)
}

@Entity(tableName = "RecentlyWatched")
class RecentlyWatched(
        @PrimaryKey
        @ColumnInfo(name = "videoId")
        val videoId: String)

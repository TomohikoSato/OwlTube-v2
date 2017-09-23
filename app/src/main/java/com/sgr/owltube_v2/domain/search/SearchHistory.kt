package com.sgr.owltube_v2.domain.search

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

@Entity(tableName = "SearchHistory")
data class SearchHistory(
        @PrimaryKey
        @ColumnInfo(name = "keyword") val keyword: String

        // UpdatedAt
) : AdapterItem
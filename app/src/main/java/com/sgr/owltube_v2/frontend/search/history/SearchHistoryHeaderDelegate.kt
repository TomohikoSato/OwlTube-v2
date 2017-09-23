package com.sgr.owltube_v2.frontend.search.history

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SearchHistoryHeaderDelegate : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return items.get(position) is SearchHistoryAdapter.SearchHistoryHeaderItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.header_search_history, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        // no op
    }
}
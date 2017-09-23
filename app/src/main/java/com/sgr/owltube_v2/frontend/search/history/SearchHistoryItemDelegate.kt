package com.sgr.owltube_v2.frontend.search.history

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemSearchHistoryBinding
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SearchHistoryItemDelegate : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SearchHistoryItemViewHolder(ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? SearchHistoryItemViewHolder)?.binding?.apply {
            searchHistory = items.get(position) as SearchHistory
        }
    }

    class SearchHistoryItemViewHolder(val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root)
}
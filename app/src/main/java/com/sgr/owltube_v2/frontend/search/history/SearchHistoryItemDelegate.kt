package com.sgr.owltube_v2.frontend.search.history

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemSearchHistoryBinding
import com.sgr.owltube_v2.domain.search.SearchHistory
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SearchHistoryItemDelegate(private val onItemClicked: (keyword: String) -> Unit,
                                private var onFillQueryButtonClicked: ((keyword: String) -> Unit))
    : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return items.get(position) is SearchHistory
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SearchHistoryItemViewHolder(ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? SearchHistoryItemViewHolder)?.binding?.run {
            searchHistory = items.get(position) as SearchHistory
            item.setOnClickListener { _ -> onItemClicked((items.get(position) as SearchHistory).keyword) }
            fillQueryButton.setOnClickListener { _ -> onFillQueryButtonClicked((items.get(position) as SearchHistory).keyword) }
        }
    }

    class SearchHistoryItemViewHolder(val binding: ItemSearchHistoryBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.sgr.owltube_v2.frontend.search.search

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.sgr.owltube_v2.databinding.SearchSuggestKeywordBinding
import com.sgr.owltube_v2.domain.search.Suggest
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SearchSuggestKeywordDelegate(private val onItemClicked: (keyword: String) -> Unit,
                                   private var onFillQueryButtonClicked: ((keyword: String) -> Unit))
    : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return items.get(position) is Suggest
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SearchSuggestKeywordViewHolder(SearchSuggestKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? SearchSuggestKeywordViewHolder)?.binding?.run {
            suggest = items.get(position) as Suggest
            container.setOnClickListener { _ -> onItemClicked((items.get(position) as Suggest).value) }
            fillQueryButton.setOnClickListener { _ -> onFillQueryButtonClicked((items.get(position) as Suggest).value) }
        }
    }
}

class SearchSuggestKeywordViewHolder(val binding: SearchSuggestKeywordBinding) : RecyclerView.ViewHolder(binding.root)

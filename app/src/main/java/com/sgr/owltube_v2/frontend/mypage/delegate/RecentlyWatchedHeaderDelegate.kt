package com.sgr.owltube_v2.frontend.mypage.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.HeaderRecentlyWatchedBinding
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.frontend.mypage.MyPageAdapter

class RecentlyWatchedHeaderDelegate(private val onClearButtonClicked: (view: View) -> Unit) :
        AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return items.get(position) is MyPageAdapter.HeaderItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            RecentlyWatchedHeaderViewHolder(
                    HeaderRecentlyWatchedBinding.inflate(
                            LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? RecentlyWatchedHeaderViewHolder)?.binding?.apply {
            clearRecentlyWatched.setOnClickListener { v -> onClearButtonClicked(v) }
        }
    }

    class RecentlyWatchedHeaderViewHolder(val binding: HeaderRecentlyWatchedBinding) : RecyclerView.ViewHolder(binding.root)
}
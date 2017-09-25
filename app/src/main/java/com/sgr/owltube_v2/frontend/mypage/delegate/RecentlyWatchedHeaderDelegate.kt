package com.sgr.owltube_v2.frontend.mypage.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.frontend.mypage.MyPageAdapter

class RecentlyWatchedHeaderDelegate : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return items.get(position) is MyPageAdapter.HeaderItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.header_recently_watched, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        // no op
    }
}
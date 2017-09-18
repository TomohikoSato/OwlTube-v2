package com.sgr.owltube_v2.frontend.common.adapter.delegate.item

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemSmallVideoBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SmallVideoItemDelegate(private val onSmallVideoItemClicked: (view: View, video: Video) -> Unit)
    : AdapterDelegate<ObservableList<AdapterItem>> {
    override fun isForViewType(items: ObservableList<AdapterItem>, position: Int): Boolean {
        return position > 0
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return SmallVideoViewHolder(ItemSmallVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<AdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? SmallVideoViewHolder)?.binding?.apply {
            video = items.get(position) as Video
            root.setOnClickListener { view -> onSmallVideoItemClicked(view, video) }
        }
    }

    class SmallVideoViewHolder(val binding: ItemSmallVideoBinding) : RecyclerView.ViewHolder(binding.root)
}
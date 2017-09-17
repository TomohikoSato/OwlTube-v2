package com.sgr.owltube_v2.frontend.player.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemPlayerRelatedVideoBinding
import com.sgr.owltube_v2.domain.player.related.RelatedVideo
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegate
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

class RelatedVideoItemDelegate : AdapterDelegate<ObservableList<PlayerAdapterItem>> {
    override fun isForViewType(items: ObservableList<PlayerAdapterItem>, position: Int): Boolean {
        return items.get(position) is RelatedVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return RelatedVideoViewHolder(ItemPlayerRelatedVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<PlayerAdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? RelatedVideoViewHolder)?.binding?.apply {
            relatedVideo = items.get(position) as RelatedVideo
        }
    }

    class RelatedVideoViewHolder(val binding: ItemPlayerRelatedVideoBinding) : RecyclerView.ViewHolder(binding.root)
}
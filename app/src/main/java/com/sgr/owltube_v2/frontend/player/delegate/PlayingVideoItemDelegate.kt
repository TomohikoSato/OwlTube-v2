package com.sgr.owltube_v2.frontend.player.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemPlayerPlayingVideoBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

class PlayingVideoItemDelegate : AdapterDelegate<ObservableList<PlayerAdapterItem>> {

    override fun isForViewType(items: ObservableList<PlayerAdapterItem>, position: Int): Boolean {
        return position == 0
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return PlayerVideoItemViewHolder(ItemPlayerPlayingVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<PlayerAdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? PlayerVideoItemViewHolder)?.binding?.apply {
            video = items.get(position) as Video
        }
    }

    class PlayerVideoItemViewHolder(val binding: ItemPlayerPlayingVideoBinding) : RecyclerView.ViewHolder(binding.root)
}
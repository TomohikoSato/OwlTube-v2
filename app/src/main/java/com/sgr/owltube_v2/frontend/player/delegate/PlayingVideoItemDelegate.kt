package com.sgr.owltube_v2.frontend.player.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.ItemPlayerPlayingVideoBinding
import com.sgr.owltube_v2.domain.player.PlayingVideo
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegate
import com.sgr.owltube_v2.frontend.player.PlayerAdapterItem

class PlayingVideoItemDelegate : AdapterDelegate<ObservableList<PlayerAdapterItem>> {

    override fun isForViewType(items: ObservableList<PlayerAdapterItem>, position: Int): Boolean {
        return items.get(position) is PlayingVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VideoDescriptionViewHolder(ItemPlayerPlayingVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: ObservableList<PlayerAdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        (holder as? VideoDescriptionViewHolder)?.binding?.apply {
            playingVideo = items.get(position) as PlayingVideo
        }
    }

    class VideoDescriptionViewHolder(val binding: ItemPlayerPlayingVideoBinding) : RecyclerView.ViewHolder(binding.root)
}
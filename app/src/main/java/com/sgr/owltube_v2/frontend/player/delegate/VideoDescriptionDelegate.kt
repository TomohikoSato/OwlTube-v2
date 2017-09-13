package com.sgr.owltube_v2.frontend.player.delegate

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegate

class VideoDescriptionDelegate : AdapterDelegate<List<PlayerAdapterItem>> {

    override fun isForViewType(items: ObservableList<PlayerAdapterItem>, position: Int): Boolean {
        return items.get(position) is VideoDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VideoDescriptionViewHolder(ItemVideoDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: List<PlayerAdapterItem>, position: Int, holder: RecyclerView.ViewHolder) {
        holder.binding.apply {
            videoDescription = items.get(position) as VideoDescription
        }
    }

    internal class VideoDescriptionViewHolder(val binding: ItemVideoDescriptionBinding) : RecyclerView.ViewHolder(binding.root)
}


//TODO
class VideoDescription : PlayerAdapterItem {

}


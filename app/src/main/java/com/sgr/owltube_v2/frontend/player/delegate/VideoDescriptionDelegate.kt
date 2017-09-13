package com.sgr.owltube_v2.frontend.player.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegate

class VideoDescriptionDelegate : AdapterDelegate<List<PlayerAdapterItem>> {
    override fun isForViewType(items: List<PlayerAdapterItem>, position: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        TODO()
        //  return VideoDescriptionViewHolder(ItemVideoDescriptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(items: List<PlayerAdapterItem>, position: Int, holder: RecyclerView.ViewHolder) =
            TODO()
/*
        holder.binding.apply {
            videoDescription = items.get(position) as VideoDescription
        }
*/

/*    internal class VideoDescriptionViewHolder(val binding: ItemVideoDescriptionBinding) : RecyclerView.ViewHolder(binding.root)*/
}


//TODO
class VideoDescription : PlayerAdapterItem {

}


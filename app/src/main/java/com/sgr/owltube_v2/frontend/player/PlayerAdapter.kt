package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.player.delegate.PlayingVideoItemDelegate
import com.sgr.owltube_v2.frontend.player.delegate.RelatedVideoItemDelegate

class PlayerAdapter(val items: ObservableList<PlayerAdapterItem>, adapterDelegatesManager: AdapterDelegatesManager<ObservableList<PlayerAdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<PlayerAdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(PlayingVideoItemDelegate())
            addDelegate(RelatedVideoItemDelegate())
        }

        items.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
            override fun onItemRangeRemoved(Ts: ObservableList<T>?, start: Int, count: Int) {
                notifyItemRangeRemoved(start, count)
            }

            override fun onItemRangeChanged(Ts: ObservableList<T>?, start: Int, count: Int) {
                notifyItemRangeChanged(start, count)
            }

            override fun onItemRangeInserted(Ts: ObservableList<T>?, start: Int, count: Int) {
                notifyItemRangeInserted(start, count)
            }

            override fun onItemRangeMoved(Ts: ObservableList<T>?, start: Int, to: Int, count: Int) {
                notifyItemMoved(start, to)
            }

            override fun onChanged(Ts: ObservableList<T>?) {
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

/**
 * [PlayerAdapter]で使用するItemを表すmarker interface.
 */
interface PlayerAdapterItem
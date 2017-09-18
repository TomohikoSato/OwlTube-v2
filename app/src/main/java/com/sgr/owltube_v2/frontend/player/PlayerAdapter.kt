package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableList
import android.view.View
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.common.adapter.delegate.item.SmallVideoItemDelegate
import com.sgr.owltube_v2.frontend.player.delegate.PlayingVideoItemDelegate

class PlayerAdapter(val items: ObservableList<PlayerAdapterItem>,
                    onSmallVideoClicked: (view: View, video: Video) -> Unit,
                    adapterDelegatesManager: AdapterDelegatesManager<ObservableList<PlayerAdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<PlayerAdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(PlayingVideoItemDelegate())
            addDelegate(SmallVideoItemDelegate(onSmallVideoClicked))
        }

        items.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<PlayerAdapterItem>>() {
            override fun onItemRangeRemoved(Ts: ObservableList<PlayerAdapterItem>?, start: Int, count: Int) {
                notifyItemRangeRemoved(start, count)
            }

            override fun onItemRangeChanged(Ts: ObservableList<PlayerAdapterItem>?, start: Int, count: Int) {
                notifyItemRangeChanged(start, count)
            }

            override fun onItemRangeInserted(Ts: ObservableList<PlayerAdapterItem>?, start: Int, count: Int) {
                notifyItemRangeInserted(start, count)
            }

            override fun onItemRangeMoved(Ts: ObservableList<PlayerAdapterItem>?, start: Int, to: Int, count: Int) {
                notifyItemMoved(start, to)
            }

            override fun onChanged(Ts: ObservableList<PlayerAdapterItem>?) {
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
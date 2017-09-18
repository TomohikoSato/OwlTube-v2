package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableList
import android.view.View
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.frontend.common.adapter.delegate.item.SmallVideoItemDelegate
import com.sgr.owltube_v2.frontend.player.delegate.PlayingVideoItemDelegate

class PlayerAdapter(val items: ObservableList<AdapterItem>,
                    onSmallVideoClicked: (view: View, video: Video) -> Unit,
                    adapterDelegatesManager: AdapterDelegatesManager<ObservableList<AdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<AdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(PlayingVideoItemDelegate())
            addDelegate(SmallVideoItemDelegate(onSmallVideoClicked))
        }

        items.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>() {
            override fun onItemRangeRemoved(Ts: ObservableList<AdapterItem>?, start: Int, count: Int) {
                notifyItemRangeRemoved(start, count)
            }

            override fun onItemRangeChanged(Ts: ObservableList<AdapterItem>?, start: Int, count: Int) {
                notifyItemRangeChanged(start, count)
            }

            override fun onItemRangeInserted(Ts: ObservableList<AdapterItem>?, start: Int, count: Int) {
                notifyItemRangeInserted(start, count)
            }

            override fun onItemRangeMoved(Ts: ObservableList<AdapterItem>?, start: Int, to: Int, count: Int) {
                notifyItemMoved(start, to)
            }

            override fun onChanged(Ts: ObservableList<AdapterItem>?) {
                notifyDataSetChanged()
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
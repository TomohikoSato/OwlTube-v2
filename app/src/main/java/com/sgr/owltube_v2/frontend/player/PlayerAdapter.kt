package com.sgr.owltube_v2.frontend.player

import android.databinding.ObservableList
import android.view.View
import com.sgr.owltube_v2.domain.player.related.RelatedVideo
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.player.delegate.PlayingVideoItemDelegate
import com.sgr.owltube_v2.frontend.player.delegate.RelatedVideoItemDelegate

class PlayerAdapter(val items: ObservableList<PlayerAdapterItem>,
                    onRelatedVideoItemClicked: (view: View, relatedVideo: RelatedVideo) -> Unit,
                    adapterDelegatesManager: AdapterDelegatesManager<ObservableList<PlayerAdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<PlayerAdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(PlayingVideoItemDelegate())
            addDelegate(RelatedVideoItemDelegate(onRelatedVideoItemClicked))
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
 * //TODO; 型を再考 AdapterItemとか
 */
interface PlayerAdapterItem
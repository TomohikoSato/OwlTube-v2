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
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

/**
 * [PlayerAdapter]で使用するItemを表すmarker interface.
 */
interface PlayerAdapterItem
package com.sgr.owltube_v2.frontend.player

import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.recycleradapter.delegate.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.player.delegate.PlayerAdapterItem
import com.sgr.owltube_v2.frontend.player.delegate.VideoDescriptionDelegate

class PlayerAdapter(val items: List<PlayerAdapterItem>) : AbsDelegationAdapter<List<PlayerAdapterItem>>(
        AdapterDelegatesManager<List<PlayerAdapterItem>>().apply { addDelegate(VideoDescriptionDelegate()) }, items) {
    override fun getItemCount(): Int {
        return items.size
    }
}
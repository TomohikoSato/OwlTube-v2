package com.sgr.owltube_v2.frontend.search

import android.databinding.ObservableList
import android.view.View
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegate
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem
import com.sgr.owltube_v2.frontend.common.adapter.delegate.item.SmallVideoItemDelegate

class SearchResultAdapter(
        val items: ObservableList<out AdapterItem>,
        onSearchResultVideoItemClicked: (view: View, video: Video) -> Unit,
        adapterDelegatesManager: AdapterDelegatesManager<ObservableList<out AdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<out AdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(SmallVideoItemDelegate(onSearchResultVideoItemClicked) as AdapterDelegate<ObservableList<out AdapterItem>>)
        }

        items.addOnListChangedCallback(
                object : ObservableList.OnListChangedCallback<Nothing>() {
                    override fun onItemRangeRemoved(Ts: Nothing, start: Int, count: Int) {
                        notifyItemRangeRemoved(start, count)
                    }

                    override fun onItemRangeChanged(Ts: Nothing, start: Int, count: Int) {
                        notifyItemRangeChanged(start, count)
                    }

                    override fun onItemRangeInserted(Ts: Nothing, start: Int, count: Int) {
                        notifyItemRangeInserted(start, count)
                    }

                    override fun onItemRangeMoved(Ts: Nothing, start: Int, to: Int, count: Int) {
                        notifyItemMoved(start, to)
                    }

                    override fun onChanged(Ts: Nothing) {
                        notifyDataSetChanged()
                    }
                })
    }

    override fun getItemCount(): Int = items.size
}


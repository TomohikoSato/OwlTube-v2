package com.sgr.owltube_v2.frontend.search.search

import android.databinding.ObservableList
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AbsDelegationAdapter
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterDelegatesManager
import com.sgr.owltube_v2.frontend.common.adapter.delegate.core.AdapterItem

class SearchAdapter(val items: ObservableList<AdapterItem>,
                    adapterDelegatesManager: AdapterDelegatesManager<ObservableList<AdapterItem>> = AdapterDelegatesManager())
    : AbsDelegationAdapter<ObservableList<AdapterItem>>(adapterDelegatesManager, items) {

    init {
        adapterDelegatesManager.apply {
            addDelegate(SearchSuggestKeywordDelegate())
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


    override fun getItemCount(): Int = items.size
}
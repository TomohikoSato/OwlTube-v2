package com.sgr.owltube_v2.frontend.common

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView

abstract class ObservableRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(items: ObservableList<T>)
    : RecyclerView.Adapter<VH>() {

    init {
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
}
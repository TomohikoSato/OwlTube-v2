package com.sgr.owltube_v2.frontend.common.recycleradapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class AdapterDelegatesManager<T> {
    fun addDelegate(delegate: AdapterDelegate<T>): AdapterDelegatesManager<T> {

    }

    fun getItemViewType(items: T, position: Int): Int {

    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    fun onBindViewHolder(items: T, position: Int, viewHolder: RecyclerView.ViewHolder) {

    }
}
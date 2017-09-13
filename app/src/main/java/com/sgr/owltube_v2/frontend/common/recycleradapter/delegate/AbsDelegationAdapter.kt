package com.sgr.owltube_v2.frontend.common.recycleradapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * An implementation of an Adapter that already uses a [AdapterDelegateManager] and calls
 * the corresponding [AdapterDelegateManager] methods from Adapter's method like [onCreateViewHolder], [onBindViewHolder], [getItemViewType].
 * So everything is already setup for you. You just have to add the [AdapterDelegate]s
 *
 * @param <T> The type of the datasoure / items
 */
abstract class AbsDelegationAdapter<T>(private val delegatesManager: AdapterDelegatesManager<T>, private val items: T)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(items, position, holder, null)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: List<*>?) {
        delegatesManager.onBindViewHolder(items, position, holder, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(items, position)
    }
}
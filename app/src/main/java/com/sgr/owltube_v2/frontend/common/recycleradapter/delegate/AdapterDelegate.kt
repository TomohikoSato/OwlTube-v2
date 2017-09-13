package com.sgr.owltube_v2.frontend.common.recycleradapter.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * @param <T> the type of adapters data source i.e. List<Accessory> </Accessory></T>
 */
interface AdapterDelegate<T> {

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param items The data source of the Adapter
     * @param position The position in the datasource
     * @return true, if this item is responsible,  otherwise false
     */
    fun isForViewType(items: T, position: Int): Boolean

    /**
     * Creates the  [RecyclerView.ViewHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return The new instantiated [RecyclerView.ViewHolder]
     */
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the datas source set
     *
     * @param items The data source
     * @param position The position in the datasource
     * @param holder The [RecyclerView.ViewHolder] to bind
     */
    fun onBindViewHolder(items: T, position: Int, holder: RecyclerView.ViewHolder)
}
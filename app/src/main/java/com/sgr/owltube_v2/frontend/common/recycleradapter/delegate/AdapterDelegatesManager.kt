package com.sgr.owltube_v2.frontend.common.recycleradapter.delegate

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class AdapterDelegatesManager<T> {
    val delegates: SparseArrayCompat<AdapterDelegate<T>> = SparseArrayCompat()

    companion object {
        const val FALLBACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1
    }

    /**
     * Adds an [AdapterDelegate].
     * **This method automatically assign internally the view type integer by using the next
     * unused**
     *
     * @param delegate the delegate to add
     * @return self
     */
    fun addDelegate(delegate: AdapterDelegate<T>): AdapterDelegatesManager<T> {
        // algorithm could be improved since there could be holes,
        // but it's very unlikely that we reach Integer.MAX_VALUE and run out of unused indexes
        var viewType = delegates.size()
        while (delegates.get(viewType) != null) {
            viewType++
            if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
                throw IllegalArgumentException(
                        "Oops, we are very close to Integer.MAX_VALUE. It seems that there are no more free and unused view type integers left to add another AdapterDelegate.")
            }
        }

        return addDelegate(viewType, delegate)
    }

    /**
     * Adds an [AdapterDelegate].
     *
     * @param viewType The viewType id
     * @param delegate The delegate to add
     */
    fun addDelegate(viewType: Int, delegate: AdapterDelegate<T>): AdapterDelegatesManager<T> {
        delegates.put(viewType, delegate);
        return this;
    }

    /**
     * Must be called from [RecyclerView.Adapter.getItemViewType]. Internally it scans all
     * the registered [AdapterDelegate] and picks the right one to return the ViewType integer.
     *
     * @param items Adapter's data source
     * @param position the position in adapters data source
     * @return the ViewType (integer). Returns {@link #FALLBACK_DELEGATE_VIEW_TYPE} in case that the
     * fallback adapter delegate should be used
     * @throws NullPointerException if no {@link AdapterDelegate} has been found that is
     * responsible for the given data element in data set (No {@link AdapterDelegate} for the given
     * ViewType)
     * @throws NullPointerException if items is null
     */
    fun getItemViewType(items: T, position: Int): Int {
        //TODO: foreach
        for (i in 0..delegates.size() - 1) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }

        throw NullPointerException(
                "No AdapterDelegate added that matches position=$position in data source")
    }

    /**
     * This method must be called in [RecyclerView.Adapter.onCreateViewHolder]}
     *
     * @param parent the parent
     * @param viewType the view type
     * @return The new created ViewHolder
     * @throws NullPointerException if no AdapterDelegate has been registered for ViewHolders
     * viewType
     */
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegate = delegates.get(viewType) ?: throw NullPointerException("No AdapterDelegate added for ViewType " + viewType)

        return delegate.onCreateViewHolder(parent)
    }

    /**
     * Must be called from[RecyclerView.Adapter.onBindViewHolder]
     *
     * @param items Adapter's data source
     * @param position the position in data source
     * @param viewHolder the ViewHolder to bind
     * @throws NullPointerException if no AdapterDelegate has been registered for ViewHolders
     * viewType
     */
    fun onBindViewHolder(items: T, position: Int, viewHolder: RecyclerView.ViewHolder) {
        val delegate = delegates.get(viewHolder.itemViewType) ?: throw NullPointerException("No AdapterDelegate added for ViewType " + viewHolder.itemViewType)
        delegate.onBindViewHolder(items, position, viewHolder)
    }
}
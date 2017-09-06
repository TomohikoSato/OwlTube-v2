package com.sgr.owltube_v2.frontend.top

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.FragmentItemBinding
import com.sgr.owltube_v2.frontend.top.TopFragment.OnTopFragmentListItemInteractionListener
import com.sgr.owltube_v2.frontend.top.dummy.DummyContent.DummyItem

internal class TopItemRecyclerViewAdapter(private val values: List<DummyItem>, private val listenerListItem: OnTopFragmentListItemInteractionListener)
    : RecyclerView.Adapter<TopItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        /*    val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_item, parent, false)

            return ViewHolder(view)
        */
    }

    override fun onBindViewHolder(holder: TopItemRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.binding.item = values[position]

/*
        holder.apply {
            item = values[position]
            idView.text = values[position].id
            contentView.text = values[position].content
            view.setOnClickListener {
                listenerListItem.onTopFragmentListItemInteraction(holder.item ?: return@setOnClickListener)
            }
        }
*/
    }

    override fun getItemCount(): Int {
        return values.size
    }

    internal class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)

/*    internal class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView
        val contentView: TextView
        var item: DummyItem? = null

        init {
            idView = view.findViewById(R.id.id)
            contentView = view.findViewById(R.id.content)
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }*/
}

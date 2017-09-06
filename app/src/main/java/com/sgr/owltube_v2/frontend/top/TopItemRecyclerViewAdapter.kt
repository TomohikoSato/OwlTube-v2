package com.sgr.owltube_v2.frontend.top

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.FragmentItemBinding
import com.sgr.owltube_v2.frontend.top.TopFragment.OnTopFragmentListItemInteractionListener
import com.sgr.owltube_v2.frontend.top.dummy.DummyContent.DummyItem

internal class TopItemRecyclerViewAdapter(private val items: List<DummyItem>, private val listenerListItem: OnTopFragmentListItemInteractionListener)
    : RecyclerView.Adapter<TopItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopItemRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.binding.item = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)
}

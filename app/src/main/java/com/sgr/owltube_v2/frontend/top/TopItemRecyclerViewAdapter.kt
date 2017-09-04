package com.sgr.owltube_v2.frontend.top

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sgr.owltube_v2.R

import com.sgr.owltube_v2.frontend.top.TopFragment.OnListFragmentInteractionListener
import com.sgr.owltube_v2.dummy.DummyContent.DummyItem

class TopItemRecyclerViewAdapter(private val values: List<DummyItem>, private val listener: OnListFragmentInteractionListener)
    : RecyclerView.Adapter<TopItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            item = values[position]
            idView.text = values[position].id
            contentView.text = values[position].content
            view.setOnClickListener {
                listener.onListFragmentInteraction(holder.item ?: return@setOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return values.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
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
    }
}

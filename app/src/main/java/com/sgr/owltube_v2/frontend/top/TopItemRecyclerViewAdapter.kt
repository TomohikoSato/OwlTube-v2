package com.sgr.owltube_v2.frontend.top

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.FragmentItemBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.top.TopFragment.OnTopFragmentListItemInteractionListener

internal class TopItemRecyclerViewAdapter(
        private val videos: ObservableList<Video>, private val listener: OnTopFragmentListItemInteractionListener)
    : RecyclerView.Adapter<TopItemRecyclerViewAdapter.ViewHolder>() {

    init {
        videos.addOnListChangedCallback(object : ObservableList.OnListChangedCallback<ObservableList<Video>>() {
            override fun onItemRangeRemoved(videos: ObservableList<Video>?, p1: Int, p2: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemRangeChanged(videos: ObservableList<Video>?, p1: Int, p2: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemRangeInserted(videos: ObservableList<Video>?, p1: Int, p2: Int) {
                notifyDataSetChanged()
            }

            override fun onItemRangeMoved(videos: ObservableList<Video>?, p1: Int, p2: Int, p3: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChanged(videos: ObservableList<Video>?) {
                notifyDataSetChanged()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopItemRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            video = videos[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    internal class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root)
}

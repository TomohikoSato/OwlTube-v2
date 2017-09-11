package com.sgr.owltube_v2.frontend.top

import android.databinding.ObservableList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sgr.owltube_v2.databinding.FragmentTopItemBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.ObservableRecyclerAdapter
import com.sgr.owltube_v2.frontend.top.TopFragment.OnTopFragmentListItemInteractionListener

internal class TopItemRecyclerViewAdapter(
        private val videos: ObservableList<Video>, private val listener: OnTopFragmentListItemInteractionListener)
    : ObservableRecyclerAdapter<Video, TopItemRecyclerViewAdapter.ViewHolder>(videos) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentTopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TopItemRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.binding.apply {
            video = videos[position]
            root.setOnClickListener { listener.onClickItem(video) }
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    internal class ViewHolder(val binding: FragmentTopItemBinding) : RecyclerView.ViewHolder(binding.root)
}

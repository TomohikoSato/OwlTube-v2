package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopFragment : DaggerFragment() {
    @Inject lateinit var topItemViewModel: TopItemViewModel

    private lateinit var listener: OnTopFragmentListItemInteractionListener

    companion object {
        fun newInstance(): TopFragment {
            return TopFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTopFragmentListItemInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTopFragmentListItemInteractionListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topItemViewModel.requestPopularVideos()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false).apply {
            findViewById<RecyclerView>(R.id.recycler_view).adapter = TopItemRecyclerViewAdapter(topItemViewModel.videos, listener)
            findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).apply {
                setColorSchemeResources(android.R.color.holo_red_dark,
                        android.R.color.holo_blue_dark, android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark)
                setOnRefreshListener {
                    isRefreshing = true
                    topItemViewModel.refreshPopularVideo().subscribe {
                        isRefreshing = false
                    }
                }
            }
        }
    }

    interface OnTopFragmentListItemInteractionListener {
        fun onClickItem(video: Video)
    }
}
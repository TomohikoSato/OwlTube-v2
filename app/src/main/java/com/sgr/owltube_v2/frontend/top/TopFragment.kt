package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.databinding.FragmentTopBinding
import com.sgr.owltube_v2.domain.Video
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopFragment : DaggerFragment() {
    @Inject lateinit var topViewModel: TopViewModel

    private lateinit var listener: TopFragmentListItemListener

    companion object {
        fun newInstance(): TopFragment {
            return TopFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TopFragmentListItemListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement TopFragmentListItemListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topViewModel.requestPopularVideos()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTopBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top, container, false)
        binding.viewModel = topViewModel

        return binding.root.apply {
            findViewById<RecyclerView>(R.id.recycler_view).adapter = TopAdapter(topViewModel.videos, listener)
            findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).apply {
                setColorSchemeResources(android.R.color.holo_red_dark,
                        android.R.color.holo_blue_dark,
                        android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark)
                setOnRefreshListener {
                    topViewModel.refreshPopularVideo()
                }
            }
        }
    }

    interface TopFragmentListItemListener {
        fun onClickItem(video: Video)
    }

    fun scrollToTop() = activity?.apply {
        findViewById<RecyclerView>(R.id.recycler_view).scrollToPosition(0)
        findViewById<AppBarLayout>(R.id.app_bar).setExpanded(true, true)
    }
}
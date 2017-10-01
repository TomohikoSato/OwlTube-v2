package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.databinding.FragmentTopBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.ScrollToTop
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TopFragment : DaggerFragment(), ScrollToTop {
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
        return binding.apply {
            viewModel = topViewModel
            recyclerView.adapter = TopAdapter(topViewModel.videos, listener)
            swipeRefresh.apply {
                setColorSchemeResources(R.color.primary)
                setOnRefreshListener {
                    topViewModel.refreshPopularVideo()
                }
            }
            errorView.errorButton.setOnClickListener { _ ->
                topViewModel.refreshPopularVideo()
            }
        }.root
    }

    interface TopFragmentListItemListener {
        fun onClickItem(video: Video)
    }
}
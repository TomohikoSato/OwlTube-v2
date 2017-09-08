package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.os.Bundle
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

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topItemViewModel.requestItems()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return (inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView).apply {
            adapter = TopItemRecyclerViewAdapter(topItemViewModel.videos, listener)
        }
    }

    interface OnTopFragmentListItemInteractionListener {
        fun onClickItem(video: Video)
    }
}
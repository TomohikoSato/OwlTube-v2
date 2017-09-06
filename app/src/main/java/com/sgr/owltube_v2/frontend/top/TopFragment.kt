package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.top.dummy.DummyContent
import com.sgr.owltube_v2.frontend.top.dummy.DummyContent.DummyItem
import dagger.android.support.DaggerFragment

class TopFragment : DaggerFragment() {
    private lateinit var listenerListItem: OnTopFragmentListItemInteractionListener

    companion object {
        fun newInstance(): TopFragment {
            return TopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return (inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView).apply {
            adapter = TopItemRecyclerViewAdapter(DummyContent.ITEMS, listenerListItem)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTopFragmentListItemInteractionListener) {
            listenerListItem = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnTopFragmentListItemInteractionListener")
        }
    }

    interface OnTopFragmentListItemInteractionListener {
        fun onTopFragmentListItemInteraction(item: DummyItem)
    }
}
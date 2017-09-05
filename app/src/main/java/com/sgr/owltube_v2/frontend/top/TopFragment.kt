package com.sgr.owltube_v2.frontend.top

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.dummy.DummyContent
import com.sgr.owltube_v2.dummy.DummyContent.DummyItem

class TopFragment : Fragment() {
    private lateinit var listener: OnListFragmentInteractionListener

    companion object {
        fun newInstance(): TopFragment {
            return TopFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return (inflater.inflate(R.layout.fragment_item_list, container, false) as RecyclerView).apply {
            adapter = TopItemRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: DummyItem)
    }
}
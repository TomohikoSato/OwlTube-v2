package com.sgr.owltube_v2.frontend.search

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import com.sgr.owltube_v2.R

class SearchFragment : Fragment() {
    private lateinit var listener: SearchFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SearchFragmentListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_search, container, false)?.apply {
            findViewById<SearchView>(R.id.search_view).setOnClickListener { view -> listener.onClickedSearchView(view) }
        }
    }

    interface SearchFragmentListener {
        fun onClickedSearchView(view: View)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}

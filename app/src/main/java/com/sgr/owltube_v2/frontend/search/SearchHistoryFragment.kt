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

class SearchHistoryFragment : Fragment() {
    private lateinit var listener: SearchHistoryFragmentListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchHistoryFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement SearchHistoryFragmentListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_search_history, container, false)?.apply {
            findViewById<SearchView>(R.id.search_view).let {
                it.setOnQueryTextFocusChangeListener { view, hasFocus ->
                    if (hasFocus) {
                        listener.onClickedSearchView(view)
                        it.clearFocus()
                    }
                }
            }
        }
    }

    interface SearchHistoryFragmentListener {
        fun onClickedSearchView(view: View)
    }

    companion object {
        fun newInstance() = SearchHistoryFragment()
    }
}

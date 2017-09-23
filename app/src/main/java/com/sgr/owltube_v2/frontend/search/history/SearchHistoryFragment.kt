package com.sgr.owltube_v2.frontend.search.history

import android.content.Context
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sgr.owltube_v2.R
import dagger.android.support.DaggerFragment

import javax.inject.Inject

class SearchHistoryFragment : DaggerFragment() {
    private lateinit var listener: SearchHistoryFragmentListener

    @Inject lateinit var viewModel: SearchHistoryViewModel

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
        viewModel.fetchSearchHistories()
        return inflater?.inflate(R.layout.fragment_search_history, container, false)?.apply {
            findViewById<TextView>(R.id.search_placeholder).setOnClickListener { view -> listener.onClickedSearchPlaceHolder(view) }
            findViewById<RecyclerView>(R.id.recycler_view).adapter = SearchHistoryAdapter(viewModel.searchHistories)
        }
    }

    interface SearchHistoryFragmentListener {
        fun onClickedSearchPlaceHolder(view: View)
    }

    companion object {
        fun newInstance() = SearchHistoryFragment()
    }
}

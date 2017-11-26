package com.sgr.owltube_v2.frontend.search.history

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.common.ScrollToTop
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchHistoryFragment : DaggerFragment(), ScrollToTop {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_history, container, false)?.apply {
            findViewById<TextView>(R.id.search_placeholder).setOnClickListener { view -> listener.onClickedSearchPlaceHolder(view) }
            findViewById<RecyclerView>(R.id.recycler_view).adapter = SearchHistoryAdapter(viewModel.searchHistories,
                    { keyword: String -> listener.onSearchHistoryClicked(keyword) },
                    { keyword: String -> listener.onFillQueryButtonClicked(keyword) })
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchSearchHistories()
    }

    interface SearchHistoryFragmentListener {
        fun onClickedSearchPlaceHolder(view: View)

        fun onSearchHistoryClicked(keyword: String)

        fun onFillQueryButtonClicked(keyword: String)
    }

    companion object {
        fun newInstance() = SearchHistoryFragment()
    }
}

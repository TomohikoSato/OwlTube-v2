package com.sgr.owltube_v2.frontend.search.result

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.databinding.ActivitySearchResultBinding
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.search.search.SearchActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchResultActivity : DaggerAppCompatActivity() {

    @Inject lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val query = intent.getStringExtra(KEY_QUERY)
        val binding = DataBindingUtil.setContentView<ActivitySearchResultBinding>(this, R.layout.activity_search_result)

        binding.apply {
            viewModel = searchResultViewModel
            up.setOnClickListener { finish() }
            searchPlaceholder.apply {
                text = query
                setOnClickListener { SearchActivity.startActivityWithTransition(this@SearchResultActivity, findViewById(R.id.search_placeholder), query) }
            }
            recyclerView.adapter = SearchResultAdapter(searchResultViewModel.searchResultVideos,
                    { _, video -> PlayerActivity.startActivity(this@SearchResultActivity, video) })
            swipeRefresh.apply {
                setColorSchemeResources(R.color.primary)
                setOnRefreshListener {
                    searchResultViewModel.search(query)
                }
            }
        }

        searchResultViewModel.search(query)
    }

    companion object {
        const val KEY_QUERY = "KEY_QUERY"

        fun startActivity(context: Context, query: String) =
                context.startActivity(Intent(context, SearchResultActivity::class.java).run {
                    putExtra(KEY_QUERY, query)
                })
    }
}

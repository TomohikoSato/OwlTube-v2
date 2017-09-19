package com.sgr.owltube_v2.frontend.search

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.databinding.ActivitySearchResultBinding
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.player.PlayerActivity
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
                setText(query)
                setOnClickListener { SearchActivity.startActivity(this@SearchResultActivity) }
            }
            recyclerView.adapter = SearchResultAdapter(searchResultViewModel.searchResultVideos,
                    { _: View, video: Video -> PlayerActivity.startActivity(this@SearchResultActivity, video) })
            swipeRefresh.apply {
                setColorSchemeResources(android.R.color.holo_red_dark,
                        android.R.color.holo_blue_dark,
                        android.R.color.holo_green_dark,
                        android.R.color.holo_orange_dark)
                setOnRefreshListener {
                    searchResultViewModel.search(query)
                }
            }
        }

        searchResultViewModel.search(query)
    }

    companion object {
        const val KEY_QUERY = "KEY_QUERY"

        fun startActivity(context: Context, query: String) {
            context.startActivity(Intent(context, SearchResultActivity::class.java).run {
                putExtra(KEY_QUERY, query)
            })
        }
    }
}

package com.sgr.owltube_v2.frontend.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchResultActivity : DaggerAppCompatActivity() {

    @Inject lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        val query = intent.getStringExtra(KEY_QUERY)
        searchResultViewModel.search(query)

        findViewById<TextView>(R.id.title).setText(query)

        findViewById<RecyclerView>(R.id.recycler_view).adapter =
                SearchResultAdapter(searchResultViewModel.searchResultVideos,
                        { _: View, video: Video -> PlayerActivity.startActivity(this, video) })

        findViewById<SwipeRefreshLayout>(R.id.swipe_refresh).apply {
            setColorSchemeResources(android.R.color.holo_red_dark,
                    android.R.color.holo_blue_dark,
                    android.R.color.holo_green_dark,
                    android.R.color.holo_orange_dark)
            setOnRefreshListener {
                searchResultViewModel.search(query)
            }
        }
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

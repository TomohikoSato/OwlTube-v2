package com.sgr.owltube_v2.frontend.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sgr.owltube_v2.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SearchResultActivity : DaggerAppCompatActivity() {

    @Inject lateinit var searchResultViewModel: SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        val query = intent.getStringExtra(KEY_QUERY)
        searchResultViewModel.search(query)
/*        findViewById<RecyclerView>(R.id.recycler_view).adapter =
                SearchResultAdapter(searchResultViewModel.searchResultVideos,
                        { view: View, relatedVideo: RelatedVideo -> PlayerActivity.startActivity(this, relatedVideo) })*/
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

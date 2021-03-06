package com.sgr.owltube_v2.frontend.search.search

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.SearchView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.search.result.SearchResultActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class SearchActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModel: SearchViewModel
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        findViewById<ImageButton>(R.id.up).setOnClickListener { _ -> finish() }
        searchView = findViewById<SearchView>(R.id.search_view).apply {
            setSearchableInfo(
                    (getSystemService(Context.SEARCH_SERVICE) as SearchManager).getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    SearchResultActivity.startActivity(this@SearchActivity, query)
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.fetchSuggestKeyword(newText)
                    return true
                }
            })
            setQuery(intent.getStringExtra(KEY_KEYWORD), false)
            findViewById<AutoCompleteTextView>(context.resources.getIdentifier("android:id/search_src_text", null, null))
                    .textSize = 14f
        }

        findViewById<RecyclerView>(R.id.recycler_view).apply {
            adapter = SearchAdapter(viewModel.items,
                    { keyword: String -> SearchResultActivity.startActivity(this@SearchActivity, keyword) },
                    { keyword: String -> searchView.setQuery(keyword, false) })
        }
    }

    companion object {
        private const val KEY_KEYWORD: String = "KEY_KEYWORD"

        /*
                fun startActivity(context: Context, keyword: String = "") {
                    context.startActivity(Intent(context, SearchActivity::class.java).apply { putExtra(KEY_KEYWORD, keyword) })
                }

        */
        fun startActivityWithTransition(activity: Activity, sharedElement: View, keyword: String = "") {
            activity.startActivity(Intent(activity, SearchActivity::class.java).apply { putExtra(KEY_KEYWORD, keyword) }/*,
                    ActivityOptions.makeSceneTransitionAnimation(activity, sharedElement, ViewCompat.getTransitionName(sharedElement)).toBundle()*/)
            //DEBUG
        }
    }
}

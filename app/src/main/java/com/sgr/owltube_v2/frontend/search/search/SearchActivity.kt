package com.sgr.owltube_v2.frontend.search.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.SearchView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.frontend.search.result.SearchResultActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        findViewById<ImageButton>(R.id.up).setOnClickListener { _ -> finish() }
        findViewById<SearchView>(R.id.search_view).apply {
            setSearchableInfo(
                    (getSystemService(Context.SEARCH_SERVICE) as SearchManager).getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    SearchResultActivity.startActivity(this@SearchActivity, query)
                    return true
                }

                override fun onQueryTextChange(p0: String): Boolean = false
            })
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }
}

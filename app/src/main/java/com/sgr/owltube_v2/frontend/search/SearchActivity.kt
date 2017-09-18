package com.sgr.owltube_v2.frontend.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sgr.owltube_v2.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        }
    }
}

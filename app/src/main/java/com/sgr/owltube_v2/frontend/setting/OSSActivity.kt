package com.sgr.owltube_v2.frontend.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.sgr.owltube_v2.R

class OSSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oss)

        findViewById<WebView>(R.id.web_view).loadUrl("file:///android_asset/licenses.html")
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, OSSActivity::class.java))
        }
    }
}

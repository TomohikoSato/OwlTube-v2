package com.sgr.owltube_v2.frontend.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sgr.owltube_v2.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(findViewById(R.id.tool_bar))

        findViewById<TextView>(R.id.text_open_source_license).setOnClickListener {
            OSSActivity.startActivity(this@SettingActivity)
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, SettingActivity::class.java))
        }
    }
}

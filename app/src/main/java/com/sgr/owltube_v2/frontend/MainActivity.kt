package com.sgr.owltube_v2.frontend

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.util.Log
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.dummy.DummyContent
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), TopFragment.OnTopFragmentListItemInteractionListener {
    @Inject lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        switchFragment()
        Log.d("hoge", retrofit.toString())
    }

    override fun onTopFragmentListItemInteraction(item: DummyContent.DummyItem) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                switchFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, TopFragment.newInstance())
                .commit()
    }
}

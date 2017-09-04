package com.sgr.owltube_v2

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.sgr.owltube_v2.dummy.DummyContent
import com.sgr.owltube_v2.frontend.top.TopFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , TopFragment.OnListFragmentInteractionListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        switchFragment()
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem) {
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

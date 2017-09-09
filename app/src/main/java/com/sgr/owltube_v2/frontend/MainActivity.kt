package com.sgr.owltube_v2.frontend

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.disableShiftingMode
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), TopFragment.OnTopFragmentListItemInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.disableShiftingMode()
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        switchFragment()
    }

    override fun onClickItem(video: Video) {
        startActivity(Intent(this, PlayerActivity::class.java))
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_top -> {
                switchFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
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

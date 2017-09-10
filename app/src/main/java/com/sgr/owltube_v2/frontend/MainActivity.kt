package com.sgr.owltube_v2.frontend

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.disableShiftingMode
import com.sgr.owltube_v2.frontend.mypage.MyPageFragment
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.search.SearchFragment
import com.sgr.owltube_v2.frontend.setting.SettingFragment
import com.sgr.owltube_v2.frontend.top.TopFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), TopFragment.OnTopFragmentListItemInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.apply {
            disableShiftingMode()
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            selectedItemId = R.id.navigation_top
        }
    }

    override fun onClickItem(video: Video) {
        startActivity(Intent(this, PlayerActivity::class.java))
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_top -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, TopFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MyPageFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SettingFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}

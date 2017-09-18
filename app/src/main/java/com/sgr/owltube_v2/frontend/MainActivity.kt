package com.sgr.owltube_v2.frontend

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.disableShiftingMode
import com.sgr.owltube_v2.frontend.mypage.MyPageFragment
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.search.SearchFragment
import com.sgr.owltube_v2.frontend.search.SearchFragment.SearchFragmentListener
import com.sgr.owltube_v2.frontend.top.TopFragment
import com.sgr.owltube_v2.frontend.top.TopFragment.TopFragmentListItemListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), TopFragmentListItemListener, SearchFragmentListener {
    private val topFragment: TopFragment by lazy {
        TopFragment.newInstance()
    }
    private val searchFragment: SearchFragment by lazy {
        SearchFragment.newInstance()
    }
    private val myPageFragment: MyPageFragment by lazy {
        MyPageFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.apply {
            disableShiftingMode()
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            setOnNavigationItemReselectedListener(onNavigationItemReselectedListener)
            selectedItemId = R.id.navigation_top
        }
        switchFragment(topFragment)
    }

    override fun onClickItem(video: Video) {
        PlayerActivity.startActivity(this, video)
    }

    override fun onClickedSearchView(view: View) {

    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_top -> {
                switchFragment(topFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                switchFragment(searchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                switchFragment(myPageFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val onNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemReselectedListener { item ->
        when (item.itemId) {
            R.id.navigation_top -> {
                topFragment.scrollToTop()
            }
            R.id.navigation_search -> {

            }
            R.id.navigation_mypage -> {
            }
        }
    }


    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}

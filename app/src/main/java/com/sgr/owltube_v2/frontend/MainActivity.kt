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
import com.sgr.owltube_v2.frontend.search.history.SearchHistoryFragment
import com.sgr.owltube_v2.frontend.search.history.SearchHistoryFragment.SearchHistoryFragmentListener
import com.sgr.owltube_v2.frontend.search.result.SearchResultActivity
import com.sgr.owltube_v2.frontend.search.search.SearchActivity
import com.sgr.owltube_v2.frontend.top.TopFragment
import com.sgr.owltube_v2.frontend.top.TopFragment.TopFragmentListItemListener
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), TopFragmentListItemListener, SearchHistoryFragmentListener {
    override fun onSearchHistoryClicked(keyword: String) {
        SearchResultActivity.startActivity(this, keyword)
    }

    override fun onFillQueryButtonClicked(keyword: String) {
        SearchActivity.startActivity(this, keyword)
    }

    private val topFragment: TopFragment by lazy {
        TopFragment.newInstance()
    }
    private val searchHistoryFragment: SearchHistoryFragment by lazy {
        SearchHistoryFragment.newInstance()
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

    override fun onClickedSearchPlaceHolder(view: View) {
        SearchActivity.startActivity(this)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_top -> {
                switchFragment(topFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                switchFragment(searchHistoryFragment)
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

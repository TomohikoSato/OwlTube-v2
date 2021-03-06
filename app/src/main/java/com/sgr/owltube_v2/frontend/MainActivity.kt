package com.sgr.owltube_v2.frontend

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.ScrollToTop
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

    private val topFragment: TopFragment by lazy { TopFragment.newInstance() }
    private val searchHistoryFragment: SearchHistoryFragment by lazy { SearchHistoryFragment.newInstance() }
    private val myPageFragment: MyPageFragment by lazy { MyPageFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.apply {
            setOnNavigationItemSelectedListener({
                switchFragment(fromNavIdToFragment(it.itemId))
                true
            })
            setOnNavigationItemReselectedListener { (fromNavIdToFragment(it.itemId) as ScrollToTop).scrollToTop(this@MainActivity) }
            selectedItemId = R.id.navigation_top
        }

        switchFragment(fromNavIdToFragment(navigation.selectedItemId))
    }

    override fun onSearchHistoryClicked(keyword: String) = SearchResultActivity.startActivity(this, keyword)

    override fun onFillQueryButtonClicked(keyword: String) =
            SearchActivity.startActivityWithTransition(this, findViewById(R.id.search_placeholder), keyword)

    override fun onClickItem(video: Video) = PlayerActivity.startActivity(this, video)

    override fun onClickedSearchPlaceHolder(view: View) =
            SearchActivity.startActivityWithTransition(this, findViewById(R.id.search_placeholder))

    private fun fromNavIdToFragment(navId: Int): Fragment =
            when (navId) {
                R.id.navigation_top -> topFragment
                R.id.navigation_search -> searchHistoryFragment
                R.id.navigation_mypage -> myPageFragment
                else -> throw IllegalArgumentException()
            }

    private fun switchFragment(fragment: Fragment) = supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
}

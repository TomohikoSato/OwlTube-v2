package com.sgr.owltube_v2.frontend.mypage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.databinding.FragmentMyPageBinding
import com.sgr.owltube_v2.frontend.common.ScrollToTop
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.setting.SettingActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyPageFragment : DaggerFragment(), ScrollToTop {

    @Inject lateinit var myPageViewModel: MyPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding: FragmentMyPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)
        myPageViewModel.fetchRecentlyWatchedVideos()

        return binding.apply {
            viewModel = myPageViewModel
            recyclerView.adapter = MyPageAdapter(myPageViewModel.items,
                    { _, video -> PlayerActivity.startActivity(this@MyPageFragment.context, video) },
                    { _ -> myPageViewModel.clearAllRecentlyWatched() })
            setting.setOnClickListener { _: View ->
                SettingActivity.startActivity(this@MyPageFragment.context)
            }
            swipeRefresh.isEnabled = false
        }.root
    }

    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }
}
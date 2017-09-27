package com.sgr.owltube_v2.frontend.mypage

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.common.ScrollToTop
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import com.sgr.owltube_v2.frontend.setting.SettingActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyPageFragment : DaggerFragment(), ScrollToTop {

    @Inject lateinit var viewModel: MyPageViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel.fetchRecentlyWatchedVideos()

        return inflater.inflate(R.layout.fragment_my_page, container, false).apply {
            findViewById<RecyclerView>(R.id.recycler_view).adapter =
                    MyPageAdapter(viewModel.items, { _: View, video: Video ->
                        PlayerActivity.startActivity(this@MyPageFragment.context, video)
                    })

            findViewById<ImageButton>(R.id.setting).setOnClickListener { _: View ->
                SettingActivity.startActivity(this@MyPageFragment.context)
            }
        }
    }

    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }
}

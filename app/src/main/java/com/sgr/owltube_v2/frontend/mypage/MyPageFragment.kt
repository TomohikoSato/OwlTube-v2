package com.sgr.owltube_v2.frontend.mypage

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.frontend.player.PlayerActivity
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyPageFragment : DaggerFragment() {

    @Inject lateinit var viewModel: MyPageViewModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        viewModel.fetchRecentlyWatchedVideos()

        return inflater.inflate(R.layout.fragment_my_page, container, false).apply {
            findViewById<RecyclerView>(R.id.recycler_view).adapter =
                    MyPageAdapter(viewModel.items, { _: View, video: Video -> PlayerActivity.startActivity(this@MyPageFragment.context, video) })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {

        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(): MyPageFragment {
            return MyPageFragment()
        }
    }
}

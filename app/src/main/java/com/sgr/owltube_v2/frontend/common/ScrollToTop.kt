package com.sgr.owltube_v2.frontend.common

import android.app.Activity
import android.support.annotation.IdRes
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.RecyclerView
import com.sgr.owltube_v2.R

interface ScrollToTop {

    fun scrollToTop(activity: Activity,
                    @IdRes recyclerViewId: Int = R.id.recycler_view,
                    @IdRes appBarId: Int = R.id.app_bar) = activity.apply {
        findViewById<RecyclerView>(recyclerViewId)?.scrollToPosition(0)
        findViewById<AppBarLayout>(appBarId)?.setExpanded(true, true)
    }
}
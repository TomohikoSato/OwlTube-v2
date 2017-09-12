package com.sgr.owltube_v2.frontend.player

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View

class FullScreenManager(private val activity: Activity) {
    fun enterFullScreen() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        hideSystemUI(activity.window.decorView)
    }

    fun exitFullScreen() {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        showSystemUI(activity.window.decorView)
    }

    //TODO: ホーム画面から戻ってきた時の挙動を修正する
    private fun hideSystemUI(decorView: View) {
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    // This snippet shows the system bars.
    private fun showSystemUI(decorView: View) {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}
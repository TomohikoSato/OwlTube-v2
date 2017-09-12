package com.sgr.owltube_v2.frontend.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video

class PlayerActivity : AppCompatActivity() {
    companion object {
        private val KEY_INTENT_EXTRA_VIDEO: String = "key_intent_extra_video"

        fun startActivity(context: Context, video: Video) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(KEY_INTENT_EXTRA_VIDEO, video)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val video = intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as Video

        val youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player)
        youtubePlayerView.initialize(object : AbstractYouTubeListener() {
            override fun onReady() {
                youtubePlayerView.loadVideo(video.id, 0F)
            }
        }, false)

        val fullScreenManager = FullScreenManager(this@PlayerActivity)
        youtubePlayerView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                fullScreenManager.enterFullScreen()
            }

            override fun onYouTubePlayerExitFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                fullScreenManager.exitFullScreen()
            }
        })
    }

    class FullScreenManager(private val activity: Activity) {
        fun enterFullScreen() {
            hideSystemUI(activity.window.decorView)
        }

        fun exitFullScreen() {
            showSystemUI(activity.window.decorView)
        }

        //TODO: ホーム画面から戻ってきた時の挙動を修正する
        // hides the system bars.
        private fun hideSystemUI(decorView: View) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        }

        // This snippet shows the system bars.
        private fun showSystemUI(mDecorView: View) {
            mDecorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}


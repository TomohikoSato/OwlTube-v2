package com.sgr.owltube_v2.frontend.player

import android.app.Activity
import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Rational
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageButton
import android.widget.RelativeLayout
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video


class PlayerActivity : AppCompatActivity() {
    companion object {
        private const val KEY_INTENT_EXTRA_VIDEO: String = "key_intent_extra_video"

        fun startActivity(context: Context, video: Video) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(KEY_INTENT_EXTRA_VIDEO, video)
            }
            context.startActivity(intent)
        }
    }

    lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val video = intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as Video

        youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player)
        youtubePlayerView.initialize(object : AbstractYouTubeListener() {
            override fun onReady() {
                youtubePlayerView.loadVideo(video.id, 0F)
            }
        }, false)

        youtubePlayerView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            val fullScreenManager = FullScreenManager(this@PlayerActivity)
            override fun onYouTubePlayerEnterFullScreen() {
                fullScreenManager.enterFullScreen()
            }

            override fun onYouTubePlayerExitFullScreen() {
                fullScreenManager.exitFullScreen()
            }
        })

        youtubePlayerView.findViewById<ViewGroup>(R.id.controls_root).addView(
                ImageButton(this).apply {
                    layoutParams = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_END)
                    }
                    val paddingPx = convertDpToPixels(8F, this@PlayerActivity)
                    setPadding(paddingPx, paddingPx, paddingPx, paddingPx)
                    setImageResource(R.drawable.ic_to_external_black_24dp)
                    setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white)))
                    setBackgroundResource(R.drawable.app_background_item_selected)

                    setOnClickListener { launchExternalView() }
                }
        )
    }

    private fun launchExternalView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPictureInPictureMode(PictureInPictureParams.Builder()
                    .setAspectRatio(Rational(4, 3))
                    .build())
        }
    }

    private fun convertDpToPixels(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

    override fun onBackPressed() {
        if (youtubePlayerView.isFullScreen) {
            youtubePlayerView.exitFullScreen()
            return
        }

        super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        youtubePlayerView.exitFullScreen()
    }

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
        private fun showSystemUI(mDecorView: View) {
            mDecorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}
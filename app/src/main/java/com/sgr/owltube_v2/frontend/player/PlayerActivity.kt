package com.sgr.owltube_v2.frontend.player

import android.app.PictureInPictureParams
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Rational
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PlayerActivity : DaggerAppCompatActivity() {
    companion object {
        private const val KEY_INTENT_EXTRA_VIDEO: String = "key_intent_extra_video"

        fun startActivity(context: Context, video: Video) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(KEY_INTENT_EXTRA_VIDEO, video)
            }
            context.startActivity(intent)
        }
    }

    enum class RepeatState(val level: Int) {
        OFF(0) {
            override fun next(): RepeatState = ON_ONE
        },
        ON_ONE(1) {
            override fun next(): RepeatState = OFF
        };

        companion object {
            fun of(value: Int): RepeatState {
                values().forEach {
                    if (it.level == value) return it
                }
                throw IllegalArgumentException()
            }
        }

        abstract fun next(): RepeatState
    }

    @Inject lateinit var viewModel: PlayerViewModel

    lateinit var youtubePlayerView: YouTubePlayerView

    private var youtubePlayerInitialized: Boolean = false

    private var repeatState: PlayerActivity.RepeatState = RepeatState.OFF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        setUp(intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as Video)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setUp(intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as Video)
    }

    private fun setUp(video: Video) {
        setUpYoutubePlayerView(video)
        viewModel.apply {
            playerItem.clear()
            playerItem.add(video)
            addRecentlyWatched(video)
            requestRelatedVideos(video.id)
        }
        findViewById<RecyclerView>(R.id.recycler_view).adapter =
                PlayerAdapter(viewModel.playerItem, { _, v: Video -> PlayerActivity.startActivity(this, v) })
    }

    override fun onBackPressed() {
        if (youtubePlayerView.isFullScreen) {
            // フルスクリーンのままアプリに戻ると画面が崩れるので、フルスクリーンを解除するようにした
            youtubePlayerView.exitFullScreen()
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && !isInPictureInPictureMode) {
            launchPinP()
            return
        }

        super.onBackPressed()
    }

    override fun onStop() {
        super.onStop()
        if (youtubePlayerView.isFullScreen) youtubePlayerView.exitFullScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        youtubePlayerView.release()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        findViewById<View>(R.id.recycler_view).visibility = if (isInPictureInPictureMode) View.GONE else View.VISIBLE
    }

    private fun setUpYoutubePlayerView(video: Video) {
        youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view).apply {
            if (youtubePlayerInitialized) {
                loadVideo(video.id, 0F)
                return
            }
            youtubePlayerInitialized = true
            initialize(object : AbstractYouTubeListener() {
                override fun onReady() {
                    loadVideo(video.id, 0F)
                }

                // https://github.com/PierfrancescoSoffritti/AndroidYouTubePlayer/blob/master/YouTubePlayer/src/main/java/com/pierfrancescosoffritti/youtubeplayer/YouTubePlayer.java
                override fun onStateChange(state: Int) {
                    super.onStateChange(state)
                    when (state) {
                        0 -> when (repeatState) { //0: ENDED
                            RepeatState.ON_ONE -> seekTo(0)
                            else -> Unit
                        }
                    }
                }

            }, false)

            addFullScreenListener(object : YouTubePlayerFullScreenListener {
                val fullScreenManager = FullScreenManager(this@PlayerActivity)
                override fun onYouTubePlayerEnterFullScreen() {
                    fullScreenManager.enterFullScreen()
                }

                override fun onYouTubePlayerExitFullScreen() {
                    fullScreenManager.exitFullScreen()
                }
            })
            setCustomActionRight(getDrawable(R.drawable.ic_repeat_levellist), { v ->
                val imageView = v as ImageView
                repeatState = repeatState.next()
                imageView.setImageLevel(repeatState.level)
            })
        }

        findViewById<ViewGroup>(R.id.controls_root).addView(
                ImageButton(this@PlayerActivity).apply {
                    layoutParams = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                        addRule(RelativeLayout.ALIGN_PARENT_END)
                    }
                    val paddingPx = convertDpToPixels(8F, this@PlayerActivity)
                    setPadding(paddingPx, paddingPx, paddingPx, paddingPx)
                    setImageResource(R.drawable.ic_to_external_black_24dp)
                    imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.white))
                    setBackgroundResource(R.drawable.app_background_item_selected)
                    setOnClickListener { launchPinP() }
                }
        )
    }

    private fun launchPinP() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            enterPictureInPictureMode(PictureInPictureParams.Builder()
                    .setAspectRatio(Rational(youtubePlayerView.width, youtubePlayerView.height))
// DEBUG: sample                   .setActions(listOf(RemoteAction(Icon.createWithResource(this, R.drawable.ic_pause_36dp), "title", "contentdescription", PendingIntent.getBroadcast(this, 333, Intent(), PendingIntent.FLAG_ONE_SHOT))))
                    .build())
        }
    }

    private fun convertDpToPixels(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}
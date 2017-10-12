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
import com.sgr.owltube_v2.common.ext.Logger
import com.sgr.owltube_v2.domain.Video
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

class PlayerActivity : DaggerAppCompatActivity() {
    companion object {
        private const val INTENT_EXTRA_KEY_VIDEO: String = "key_intent_extra_video"
        private const val STATE_KEY_REPEAT_STATE = "statekeyrepeatstate"

        fun startActivity(context: Context, video: Video) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(INTENT_EXTRA_KEY_VIDEO, video)
            }
            context.startActivity(intent)
        }
    }

    private val youtubePlayerView: YouTubePlayerView by lazy {
        findViewById<YouTubePlayerView>(R.id.youtube_player_view)
    }

    @Inject lateinit var viewModel: PlayerViewModel

    private var youtubePlayerInitialized: Boolean = false

    private var repeatState: RepeatState = RepeatState.OFF

    private var playedVideos: Stack<Video> = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("onCreate")
        setContentView(R.layout.activity_player)
        handleIntent(intent)
        savedInstanceState?.let {
            repeatState = RepeatState.of(it.getInt(STATE_KEY_REPEAT_STATE))
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(STATE_KEY_REPEAT_STATE, repeatState.level)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Logger.d("onNewIntent")
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val video = intent.getSerializableExtra(INTENT_EXTRA_KEY_VIDEO) as Video
        intent.removeExtra(INTENT_EXTRA_KEY_VIDEO)

        setUpYoutubePlayerView(video)
        viewModel.apply {
            playerItem.clear()
            playerItem.add(video)
            addRecentlyWatched(video)
            requestRelatedVideos(video.id)
        }
        findViewById<RecyclerView>(R.id.recycler_view).adapter =
                PlayerAdapter(viewModel.playerItem, { _, nextVideo: Video ->
                    playedVideos.push(video)
                    PlayerActivity.startActivity(this, nextVideo)
                })
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
        Logger.d("onDestroy")
        youtubePlayerView.release()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        findViewById<View>(R.id.recycler_view).visibility = if (isInPictureInPictureMode) View.GONE else View.VISIBLE
    }

    private fun playPreviousVideo() =
            if (playedVideos.empty()) Unit else PlayerActivity.startActivity(this, playedVideos.pop())

    private fun setUpYoutubePlayerView(video: Video) {
        youtubePlayerView.apply {
            if (youtubePlayerInitialized) {
                loadVideo(video.id, 0F)
                return
            }
            initialize(object : AbstractYouTubeListener() {
                override fun onReady() {
                    youtubePlayerInitialized = true
                    Logger.d("onready")
                    playedVideos.clear()
                    loadVideo(video.id, 0F)
                }

                // https://github.com/PierfrancescoSoffritti/AndroidYouTubePlayer/blob/master/YouTubePlayer/src/main/java/com/pierfrancescosoffritti/youtubeplayer/YouTubePlayer.java
                override fun onStateChange(state: Int) {
                    super.onStateChange(state)
                    when (state) {
                        0 -> when (repeatState) { //0: ENDED
                            RepeatState.ON_ONE -> seekTo(0)
                            else -> playedVideos.push(video)
                        }
                    }
                }

            }, false)

            addFullScreenListener(object : YouTubePlayerFullScreenListener {
                val fullScreenManager = FullScreenManager(this@PlayerActivity)
                override fun onYouTubePlayerEnterFullScreen() = fullScreenManager.enterFullScreen()
                override fun onYouTubePlayerExitFullScreen() = fullScreenManager.exitFullScreen()
            })
            setCustomActionRight(getDrawable(R.drawable.ic_repeat_levellist), { v ->
                repeatState = repeatState.next()
                (v as ImageView).setImageLevel(repeatState.level)
            })
            setCustomActionLeft(getDrawable(R.drawable.ic_skip_previous_black_24dp), { _ ->
                playPreviousVideo()
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
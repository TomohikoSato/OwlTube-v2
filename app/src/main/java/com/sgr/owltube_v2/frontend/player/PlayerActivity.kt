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
import android.widget.RelativeLayout
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.youtubeplayer.YouTubePlayerView
import com.sgr.owltube_v2.R
import com.sgr.owltube_v2.domain.Video
import com.sgr.owltube_v2.domain.player.PlayingVideo
import com.sgr.owltube_v2.domain.player.related.RelatedVideo
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class PlayerActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModel: PlayerViewModel

    companion object {
        private const val KEY_INTENT_EXTRA_VIDEO: String = "key_intent_extra_video"

        fun startActivity(context: Context, video: Video) {
            val intent = Intent(context, PlayerActivity::class.java).apply {
                putExtra(KEY_INTENT_EXTRA_VIDEO, PlayingVideo(video))
            }
            context.startActivity(intent)
        }
    }

    lateinit var youtubePlayerView: YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        setUp(intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as PlayingVideo)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setUp(intent.getSerializableExtra(KEY_INTENT_EXTRA_VIDEO) as PlayingVideo)
    }

    private fun setUp(video: PlayingVideo) {
        setUpYoutubePlayerView(video)
        viewModel.playerItem.clear()
        viewModel.playerItem.add(video)
        viewModel.requestRelatedVideos(video.id)
        findViewById<RecyclerView>(R.id.recycler_view).adapter =
                PlayerAdapter(viewModel.playerItem, { view: View, relatedVideo: RelatedVideo -> PlayerActivity.startActivity(this, relatedVideo) })
    }

    override fun onBackPressed() {
        if (youtubePlayerView.isFullScreen) {
            // フルスクリーンのままアプリに戻ると画面が崩れるので、フルスクリーンを解除するようにした
            youtubePlayerView.exitFullScreen()
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

    private var youtubePlayerInitialized: Boolean = false

    private fun setUpYoutubePlayerView(video: Video) {
        youtubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)

        if (youtubePlayerInitialized) {
            youtubePlayerView.loadVideo(video.id, 0F)
        } else {
            youtubePlayerInitialized = true
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
                        setOnClickListener { launchPinP() }
                    }
            )

        }


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
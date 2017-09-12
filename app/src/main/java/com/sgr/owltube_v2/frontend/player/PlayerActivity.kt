package com.sgr.owltube_v2.frontend.player

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pierfrancescosoffritti.youtubeplayer.AbstractYouTubeListener
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

        val youtubePlayer = findViewById<YouTubePlayerView>(R.id.youtube_player)
        youtubePlayer.initialize(object : AbstractYouTubeListener() {
            override fun onReady() {
                youtubePlayer.loadVideo(video.id, 0F)
            }
        }, false)
    }
}

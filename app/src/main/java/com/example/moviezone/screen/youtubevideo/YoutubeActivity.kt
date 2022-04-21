package com.example.moviezone.screen.youtubevideo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.example.moviezone.R
import com.example.moviezone.databinding.YoutubeVideoBinding
import com.example.moviezone.utils.Const
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import javax.security.auth.login.LoginException

class YoutubeActivity: YouTubeBaseActivity() {

    private var binding: YoutubeVideoBinding? = null
    private var player: YouTubePlayer? = null
    private var savedTime = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hideSystemUI()

        if (savedInstanceState != null) {
            savedTime = savedInstanceState.getInt("currentTime")
        }

        binding = DataBindingUtil.setContentView(this, R.layout.youtube_video)

        binding?.wvTrailerYoutube?.initialize(Const.YOUTUBE_API_KEY, object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (p1 != null) {
                    player = p1
                    p1.loadVideo(intent.getStringExtra("Trailer"), savedTime)
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                finish()
            }

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        player?.let { outState.putInt("currentTime", it.currentTimeMillis) }
        super.onSaveInstanceState(outState)
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
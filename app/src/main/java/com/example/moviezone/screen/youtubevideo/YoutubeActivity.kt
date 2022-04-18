package com.example.moviezone.screen.youtubevideo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.moviezone.R
import com.example.moviezone.databinding.YoutubeVideoBinding
import com.example.moviezone.utils.Const
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class YoutubeActivity: YouTubeBaseActivity() {

    private var binding: YoutubeVideoBinding? = null
    private var player: YouTubePlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.youtube_video)

        binding?.wvTrailerYoutube?.initialize(Const.YOUTUBE_API_KEY, object: YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {

                if (p1 != null) {
                    player = p1
                    player?.loadVideo(intent.getStringExtra("Trailer"))
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.e("YOUTUBE", "onInitializationFailure ${p1?.name}")
                finish()
            }

        })
    }
}
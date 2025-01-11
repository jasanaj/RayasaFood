package com.example.rayasafood

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rayasafood.databinding.ActivityDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailActivity : AppCompatActivity() {
    private val binding : ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view for the activity
        setContentView(binding.root)

        // Apply edge-to-edge insets (ensure status bar and navigation bar are handled)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize YouTubePlayerView
        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)

        // Observe lifecycle for the YouTubePlayerView
        lifecycle.addObserver(youTubePlayerView)

        // Set up the YouTube player listener
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = "S0Q4gqBUs7c"  // Video ID to play
                youTubePlayer.loadVideo(videoId, 0f)  // Start the video immediately
            }
        })

        // Retrieve the data passed from HomeFragment
        val foodName = intent.getStringExtra("FOOD_NAME")
        // Use the foodName in your activity (e.g., display it on a TextView)
        findViewById<TextView>(R.id.food_name).text = foodName

        // Back button logic
        binding.backBtn.setOnClickListener {
            // Use finish() to close the current activity and return to the previous one
            finish()
        }
    }
}

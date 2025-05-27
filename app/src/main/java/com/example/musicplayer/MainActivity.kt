package com.example.musicplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupPlayerControls()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupPlayerControls() {
        binding.playerControls.playPauseButton.setOnClickListener {
            // TODO: Implement play/pause functionality
        }

        binding.playerControls.previousButton.setOnClickListener {
            // TODO: Implement previous track functionality
        }

        binding.playerControls.nextButton.setOnClickListener {
            // TODO: Implement next track functionality
        }
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        // Handle configuration changes (including orientation changes)
        // The layout will automatically adapt to the new orientation
    }
} 
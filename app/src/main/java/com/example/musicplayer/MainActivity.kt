package com.example.musicplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicplayer.api.MusicService
import com.example.musicplayer.api.MusicServiceFactory
import com.example.musicplayer.api.ServiceType
import com.example.musicplayer.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var musicServiceFactory: MusicServiceFactory
    private var currentService: MusicService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        musicServiceFactory = MusicServiceFactory(this)
        setupUI()
        setupServiceTabs()
        setupPlayerControls()
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupServiceTabs() {
        binding.serviceTabs.addTab(binding.serviceTabs.newTab().setText("Spotify"))
        binding.serviceTabs.addTab(binding.serviceTabs.newTab().setText("Apple Music"))

        binding.serviceTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> switchService(ServiceType.SPOTIFY)
                    1 -> switchService(ServiceType.APPLE_MUSIC)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Select Spotify by default
        binding.serviceTabs.getTabAt(0)?.select()
    }

    private fun switchService(serviceType: ServiceType) {
        currentService = musicServiceFactory.getService(serviceType)
        lifecycleScope.launch {
            if (!currentService?.isAuthenticated()!!) {
                currentService?.authenticate()
            }
        }
    }

    private fun setupPlayerControls() {
        binding.playerControls.playPauseButton.setOnClickListener {
            lifecycleScope.launch {
                currentService?.let { service ->
                    if (service.isAuthenticated()) {
                        // TODO: Implement play/pause functionality
                    }
                }
            }
        }

        binding.playerControls.previousButton.setOnClickListener {
            lifecycleScope.launch {
                currentService?.let { service ->
                    if (service.isAuthenticated()) {
                        service.skipToPrevious()
                    }
                }
            }
        }

        binding.playerControls.nextButton.setOnClickListener {
            lifecycleScope.launch {
                currentService?.let { service ->
                    if (service.isAuthenticated()) {
                        service.skipToNext()
                    }
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        // Handle configuration changes (including orientation changes)
        // The layout will automatically adapt to the new orientation
    }
} 
package com.example.musicplayer.api

import android.content.Context

class MusicServiceFactory(private val context: Context) {
    private val spotifyService: SpotifyService by lazy { SpotifyService(context) }
    private val appleMusicService: AppleMusicService by lazy { AppleMusicService(context) }

    fun getService(type: ServiceType): MusicService {
        return when (type) {
            ServiceType.SPOTIFY -> spotifyService
            ServiceType.APPLE_MUSIC -> appleMusicService
        }
    }

    fun getAllServices(): List<MusicService> {
        return listOf(spotifyService, appleMusicService)
    }
} 
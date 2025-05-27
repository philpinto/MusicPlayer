package com.example.musicplayer.api

import kotlinx.coroutines.flow.Flow

interface MusicService {
    suspend fun authenticate()
    suspend fun search(query: String): Flow<List<Track>>
    suspend fun getPlaylists(): Flow<List<Playlist>>
    suspend fun getLibrary(): Flow<List<Track>>
    suspend fun play(track: Track)
    suspend fun pause()
    suspend fun resume()
    suspend fun skipToNext()
    suspend fun skipToPrevious()
    suspend fun isAuthenticated(): Boolean
}

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val albumArt: String,
    val previewUrl: String?,
    val serviceType: ServiceType
)

data class Playlist(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val serviceType: ServiceType
)

enum class ServiceType {
    SPOTIFY,
    APPLE_MUSIC
} 
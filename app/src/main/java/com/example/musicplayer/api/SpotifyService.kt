package com.example.musicplayer.api

import android.content.Context
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpotifyService(private val context: Context) : MusicService {
    private var spotifyAppRemote: SpotifyAppRemote? = null
    private var isAuthenticated = false

    override suspend fun authenticate() {
        val request = AuthorizationRequest.Builder(
            CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            REDIRECT_URI
        ).setScopes(arrayOf("streaming", "user-read-private", "user-read-email"))
            .build()

        AuthorizationClient.openLoginActivity(context as android.app.Activity, request)
    }

    override suspend fun search(query: String): Flow<List<Track>> = flow {
        // TODO: Implement Spotify search
        emit(emptyList())
    }

    override suspend fun getPlaylists(): Flow<List<Playlist>> = flow {
        // TODO: Implement Spotify playlists
        emit(emptyList())
    }

    override suspend fun getLibrary(): Flow<List<Track>> = flow {
        // TODO: Implement Spotify library
        emit(emptyList())
    }

    override suspend fun play(track: Track) {
        spotifyAppRemote?.playerApi?.play(track.previewUrl)
    }

    override suspend fun pause() {
        spotifyAppRemote?.playerApi?.pause()
    }

    override suspend fun resume() {
        spotifyAppRemote?.playerApi?.resume()
    }

    override suspend fun skipToNext() {
        spotifyAppRemote?.playerApi?.skipNext()
    }

    override suspend fun skipToPrevious() {
        spotifyAppRemote?.playerApi?.skipPrevious()
    }

    override suspend fun isAuthenticated(): Boolean = isAuthenticated

    private fun connectToSpotify() {
        val connectionParams = ConnectionParams.Builder(CLIENT_ID)
            .setRedirectUri(REDIRECT_URI)
            .showAuthView(true)
            .build()

        SpotifyAppRemote.connect(context, connectionParams, object : Connector.ConnectionListener {
            override fun onConnected(appRemote: SpotifyAppRemote) {
                spotifyAppRemote = appRemote
                isAuthenticated = true
            }

            override fun onFailure(error: Throwable) {
                isAuthenticated = false
            }
        })
    }

    companion object {
        private const val CLIENT_ID = "YOUR_SPOTIFY_CLIENT_ID"
        private const val REDIRECT_URI = "com.example.musicplayer://callback"
    }
} 
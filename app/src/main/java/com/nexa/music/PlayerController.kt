package com.nexa.music

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class PlayerController(private val context: Context) {
    private var player: MediaPlayer? = null
    var current: Track? = null
        private set
    var isPlaying: Boolean = false
        private set

    fun play(track: Track) {
        player?.release()
        player = MediaPlayer.create(context, Uri.parse(track.uri))?.apply {
            setOnCompletionListener { isPlaying = false }
            start()
        }
        current = track
        isPlaying = player != null
    }

    fun pause() {
        player?.pause()
        isPlaying = false
    }

    fun resume() {
        player?.start()
        isPlaying = true
    }

    fun stop() {
        player?.stop()
        player?.release()
        player = null
        current = null
        isPlaying = false
    }

    fun release() { player?.release(); player = null }
}

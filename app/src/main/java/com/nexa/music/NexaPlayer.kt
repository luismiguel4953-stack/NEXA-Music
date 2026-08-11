package com.nexa.music

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri

class NexaPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var currentTrack: Track? = null
        private set

    fun play(track: Track) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(context, Uri.parse(track.uri))
            setOnPreparedListener { it.start() }
            prepareAsync()
        }
        currentTrack = track
    }

    fun toggle() {
        mediaPlayer?.let { if (it.isPlaying) it.pause() else it.start() }
    }

    fun isPlaying(): Boolean = mediaPlayer?.isPlaying == true
    fun position(): Int = mediaPlayer?.currentPosition ?: 0
    fun duration(): Int = mediaPlayer?.duration ?: 0
    fun seekTo(position: Int) { mediaPlayer?.seekTo(position) }
    fun release() { mediaPlayer?.release(); mediaPlayer = null; currentTrack = null }
}

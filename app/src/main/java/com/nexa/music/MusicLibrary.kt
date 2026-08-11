package com.nexa.music

import android.content.ContentResolver
import android.provider.MediaStore

data class Track(val id: Long, val title: String, val artist: String, val album: String, val duration: Long, val uri: String)

object MusicLibrary {
    fun load(contentResolver: ContentResolver): List<Track> {
        val tracks = mutableListOf<Track>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.CONTENT_URI.toString()
        )
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use { cursor ->
            val id = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val title = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artist = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val album = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val duration = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            while (cursor.moveToNext()) {
                val trackId = cursor.getLong(id)
                tracks += Track(
                    trackId,
                    cursor.getString(title) ?: "Sin título",
                    cursor.getString(artist) ?: "Artista desconocido",
                    cursor.getString(album) ?: "Álbum desconocido",
                    cursor.getLong(duration),
                    "content://media/external/audio/media/$trackId"
                )
            }
        }
        return tracks
    }
}

package com.nexa.music

import android.content.ContentResolver
import android.provider.MediaStore

object DeviceMusicRepository {
    fun songs(resolver: ContentResolver): List<Track> {
        val result = mutableListOf<Track>()
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DURATION
        )
        resolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            "${MediaStore.Audio.Media.IS_MUSIC} != 0",
            null,
            "${MediaStore.Audio.Media.TITLE} COLLATE NOCASE ASC"
        )?.use { c ->
            val id = c.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val title = c.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artist = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val album = c.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
            val duration = c.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)
            while (c.moveToNext()) {
                val songId = c.getLong(id)
                result += Track(
                    songId,
                    c.getString(title) ?: "Sin título",
                    c.getString(artist) ?: "Artista desconocido",
                    c.getString(album) ?: "Álbum desconocido",
                    c.getLong(duration),
                    "content://media/external/audio/media/$songId"
                )
            }
        }
        return result
    }
}

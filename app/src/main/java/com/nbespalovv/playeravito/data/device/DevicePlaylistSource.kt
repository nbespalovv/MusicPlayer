package com.nbespalovv.playeravito.data.device

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.nbespalovv.playeravito.model.dto.SongLocal

class DevicePlaylistSource {
    fun getLocalPlaylist(context: Context): List<SongLocal> {
        val songs = mutableListOf<SongLocal>()
        val contentResolver = context.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        contentResolver.query(uri, null, selection, null, sortOrder)?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
            val artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
            val albumIdColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn) ?: "Unknown Title"
                val artist = cursor.getString(artistColumn) ?: "Unknown Artist"
                val albumId = cursor.getLong(albumIdColumn)

                val songPreview: String =
                    ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
                        .toString()

                val albumCover: Uri = ContentUris.withAppendedId(
                    MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                    albumId
                )

                songs.add(SongLocal(
                    id = id,
                    title = title,
                    preview = songPreview,
                    artist = artist,
                    album = albumCover.toString()
                ))
            }
        }
        return songs
    }
}
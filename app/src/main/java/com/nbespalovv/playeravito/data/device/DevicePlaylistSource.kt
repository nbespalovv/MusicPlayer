package com.nbespalovv.playeravito.data.device

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import com.nbespalovv.playeravito.model.dto.SongLocal

class DevicePlaylistSource {
    fun getLocalPlaylist(context: Context): List<SongLocal> {
        return getCursor(context)?.let { cursor ->
            walkCursor(cursor)
        } ?: emptyList()
    }

    fun searchLocalPlaylist(context: Context, query: String): List<SongLocal> {
        return getCursor(context, query)?.let { cursor ->
            walkCursor(cursor)
        } ?: emptyList()
    }

    private fun getCursor(context: Context, query: String? = null): Cursor? {
        val contentResolver = context.contentResolver
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

        val selection = query?.let {
            "${MediaStore.Audio.Media.IS_MUSIC} != 0 " +
                    "AND (${MediaStore.Audio.Media.TITLE} LIKE ?" +
                    " OR ${MediaStore.Audio.Media.ARTIST} LIKE ?)"
        }
            ?: "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = "${MediaStore.Audio.Media.TITLE} ASC"

        return contentResolver.query(
            uri,
            null,
            selection,
            query?.let { arrayOf("%$it%", "%$it%") },
            sortOrder
        )
    }

    private fun walkCursor(cursor: Cursor): List<SongLocal> {
        val songs = mutableListOf<SongLocal>()

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

            songs.add(
                SongLocal(
                    id = id,
                    title = title,
                    preview = songPreview,
                    artist = artist,
                    album = albumCover.toString()
                )
            )
        }
        return songs
    }
}
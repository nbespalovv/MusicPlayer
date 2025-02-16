package com.nbespalovv.playeravito.model.mappers

import com.nbespalovv.playeravito.model.common.Song
import com.nbespalovv.playeravito.model.dto.SongLocal
import com.nbespalovv.shared.SongShared

fun Song.toSongShared(): SongShared {
    return SongShared(
        id = id,
        title = title,
        preview = preview,
        artist = artist,
        album = album
    )
}

fun SongLocal.toSong(): Song {
    return Song(
        id = id,
        title = title,
        preview = preview,
        artist = artist,
        album = album
    )
}
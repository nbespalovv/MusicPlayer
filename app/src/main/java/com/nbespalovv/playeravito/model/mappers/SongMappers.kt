package com.nbespalovv.playeravito.model.mappers

import com.nbespalovv.playeravito.model.dto.SongLocal
import com.nbespalovv.playeravito.dto.SongDto
import com.nbespalovv.playeravito.model.common.Song
import com.nbespalovv.playeravito.model.ui.SongUI
import com.nbespalovv.shared.SongShared

fun SongDto.toSong(): Song {
    return Song(
        id = id,
        title = title,
        preview = preview,
        artist = artist.name,
        album = album.image
    )
}

fun Song.toSongUI(): SongUI {
    return SongUI(
        id = id,
        title = title,
        preview = preview,
        artist = artist,
        album = album
    )
}

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
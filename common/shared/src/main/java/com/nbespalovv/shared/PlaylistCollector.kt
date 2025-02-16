package com.nbespalovv.shared

fun interface PlaylistCollector {
    fun submitPlaylist(playlist: List<SongShared>) : Unit
}
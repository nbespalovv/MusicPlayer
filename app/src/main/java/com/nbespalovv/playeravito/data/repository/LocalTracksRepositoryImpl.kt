package com.nbespalovv.playeravito.data.repository

import android.content.Context
import com.nbespalovv.playeravito.data.device.DevicePlaylistSource
import com.nbespalovv.playeravito.model.common.Song
import com.nbespalovv.playeravito.model.mappers.toSong
import com.nbespalovv.utils.DataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LocalTracksRepositoryImpl @Inject constructor(
    private val context: Context,
    private val source: DevicePlaylistSource,
) : TracksRepository {
    private val _playlist = MutableStateFlow<List<Song>>(emptyList())
    override val playlist: StateFlow<List<Song>>
        get() = _playlist

    override suspend fun loadChart(): DataState<Unit> {
        source.getLocalPlaylist(context).also {
            _playlist.emit(it.map { it.toSong() })
        }
        return DataState.Success(Unit)
    }
}
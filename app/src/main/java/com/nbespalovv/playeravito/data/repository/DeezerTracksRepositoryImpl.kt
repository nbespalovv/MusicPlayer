package com.nbespalovv.playeravito.data.repository

import com.nbespalovv.playeravito.data.api.ApiService
import com.nbespalovv.utils.DataState
import com.nbespalovv.playeravito.model.common.Song
import com.nbespalovv.utils.asUnit
import com.nbespalovv.utils.handleState
import com.nbespalovv.playeravito.model.mappers.toSong
import com.nbespalovv.utils.toDataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeezerTracksRepositoryImpl @Inject constructor(
    private val service: ApiService,
) : TracksRepository {
    private val _playlist = MutableStateFlow<List<Song>>(emptyList())

    override val playlist: StateFlow<List<Song>>
        get() = _playlist

    override suspend fun loadChart(): DataState<Unit> = withContext(Dispatchers.IO) {
        service
            .loadChart()
            .toDataState()
            .handleState(
                onSuccess = {
                    it.data.map { it.toSong() }.also { result ->
                        _playlist.emit(result)
                    }
                }
            )
            .asUnit()
    }

    companion object {
        const val NAME_KEY = "DeezerTracksRepositoryImpl"
    }
}
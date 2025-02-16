package com.nbespalovv.playeravito.data.repository

import com.nbespalovv.utils.DataState
import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.flow.StateFlow

interface TracksRepository {
    val playlist: StateFlow<List<Song>>
    suspend fun loadChart(): DataState<Unit>
    suspend fun search(query: String): DataState<Unit>
}
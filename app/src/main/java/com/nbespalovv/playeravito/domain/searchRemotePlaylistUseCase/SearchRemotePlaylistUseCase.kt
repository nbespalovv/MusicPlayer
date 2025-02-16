package com.nbespalovv.playeravito.domain.searchRemotePlaylistUseCase

import com.nbespalovv.utils.DataState

interface SearchRemotePlaylistUseCase {
    suspend operator fun invoke(query: String): DataState<Unit>
}
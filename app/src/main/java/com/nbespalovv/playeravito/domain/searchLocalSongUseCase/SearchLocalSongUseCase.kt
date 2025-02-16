package com.nbespalovv.playeravito.domain.searchLocalSongUseCase

import com.nbespalovv.utils.DataState

interface SearchLocalSongUseCase {
    suspend operator fun invoke(query: String): DataState<Unit>
}
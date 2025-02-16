package com.nbespalovv.playeravito.domain.localTracksUseCase

import com.nbespalovv.utils.DataState

interface LocalTracksUseCase {
    suspend operator fun invoke(): DataState<Unit>
}
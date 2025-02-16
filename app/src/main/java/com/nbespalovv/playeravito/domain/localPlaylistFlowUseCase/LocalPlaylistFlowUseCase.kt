package com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase

import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.flow.StateFlow

interface LocalPlaylistFlowUseCase {
    operator fun invoke(): StateFlow<List<Song>>
}
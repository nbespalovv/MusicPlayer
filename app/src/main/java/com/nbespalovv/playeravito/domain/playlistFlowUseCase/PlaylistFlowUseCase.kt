package com.nbespalovv.playeravito.domain.playlistFlowUseCase

import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.flow.StateFlow

interface PlaylistFlowUseCase {
    operator fun invoke(): StateFlow<List<Song>>
}


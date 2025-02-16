package com.nbespalovv.playeravito.domain.playlistFlowUseCase

import com.nbespalovv.playeravito.data.repository.DeezerTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Named

internal class PlaylistFlowUseCaseImpl @Inject constructor(
    @Named(DeezerTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
): PlaylistFlowUseCase {
    override fun invoke(): StateFlow<List<Song>> =
        repository.playlist
}
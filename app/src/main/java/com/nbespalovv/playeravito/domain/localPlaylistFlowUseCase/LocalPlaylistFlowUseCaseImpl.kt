package com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase

import com.nbespalovv.playeravito.data.repository.LocalTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.playeravito.model.common.Song
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Named

internal class LocalPlaylistFlowUseCaseImpl @Inject constructor(
    @Named(LocalTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
): LocalPlaylistFlowUseCase {
    override fun invoke(): StateFlow<List<Song>> =
        repository.playlist
}
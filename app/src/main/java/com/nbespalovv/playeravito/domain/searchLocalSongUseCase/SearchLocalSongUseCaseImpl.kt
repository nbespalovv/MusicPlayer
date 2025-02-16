package com.nbespalovv.playeravito.domain.searchLocalSongUseCase

import com.nbespalovv.playeravito.data.repository.LocalTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.utils.DataState
import javax.inject.Inject
import javax.inject.Named

internal class SearchLocalSongUseCaseImpl @Inject constructor(
    @Named(LocalTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
) : SearchLocalSongUseCase {
    override suspend fun invoke(query: String): DataState<Unit> =
        repository.search(query)
}
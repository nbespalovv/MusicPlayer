package com.nbespalovv.playeravito.domain.searchRemotePlaylistUseCase

import com.nbespalovv.playeravito.data.repository.DeezerTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.utils.DataState
import javax.inject.Inject
import javax.inject.Named

internal class SearchRemotePlaylistUseCaseImpl @Inject constructor(
   @Named(DeezerTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
): SearchRemotePlaylistUseCase {
    override suspend fun invoke(query: String): DataState<Unit> =
        repository.search(query)
}
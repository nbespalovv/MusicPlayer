package com.nbespalovv.playeravito.domain.localTracksUseCase

import com.nbespalovv.playeravito.data.repository.LocalTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.utils.DataState
import javax.inject.Inject
import javax.inject.Named

internal class LocalTracksUseCaseImpl @Inject constructor(
    @Named(LocalTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
) : LocalTracksUseCase {
    override suspend fun invoke(): DataState<Unit> =
        repository.loadChart()
}
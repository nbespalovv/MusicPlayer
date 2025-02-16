package com.nbespalovv.playeravito.domain.loadChartsUseCase

import com.nbespalovv.playeravito.data.repository.DeezerTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.utils.DataState
import javax.inject.Inject
import javax.inject.Named

internal class LoadChartsUseCaseImpl @Inject constructor(
    @Named(DeezerTracksRepositoryImpl.NAME_KEY) private val repository: TracksRepository,
): LoadChartsUseCase {
    override suspend fun invoke(): DataState<Unit> =
        repository.loadChart()
}
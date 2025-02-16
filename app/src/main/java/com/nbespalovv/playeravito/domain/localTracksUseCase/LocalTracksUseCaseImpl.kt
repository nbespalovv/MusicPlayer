package com.nbespalovv.playeravito.domain.localTracksUseCase

import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.utils.DataState
import javax.inject.Inject

internal class LocalTracksUseCaseImpl @Inject constructor(
    private val repository: TracksRepository,
) : LocalTracksUseCase {
    override suspend fun invoke(): DataState<Unit> =
        repository.loadChart()
}
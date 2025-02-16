package com.nbespalovv.playeravito.domain.loadChartsUseCase

import com.nbespalovv.utils.DataState

interface LoadChartsUseCase {
    suspend operator fun invoke(): DataState<Unit>
}


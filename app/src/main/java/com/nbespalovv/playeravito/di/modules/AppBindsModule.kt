package com.nbespalovv.playeravito.di.modules

import android.app.Application
import android.content.Context
import com.nbespalovv.playeravito.data.device.DevicePlaylistSource
import com.nbespalovv.playeravito.data.repository.LocalTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase.LocalPlaylistFlowUseCase
import com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase.LocalPlaylistFlowUseCaseImpl
import com.nbespalovv.playeravito.domain.localTracksUseCase.LocalTracksUseCase
import com.nbespalovv.playeravito.domain.localTracksUseCase.LocalTracksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface AppBindsModule {

    @Binds
    @Singleton
    fun bindLocalTracksRepository(
        repository: LocalTracksRepositoryImpl
    ) : TracksRepository

    @Binds
    fun bindLocalTracksUseCase(impl: LocalTracksUseCaseImpl) : LocalTracksUseCase

    @Binds
    fun bindLocalPlaylistFlowUseCase(impl: LocalPlaylistFlowUseCaseImpl): LocalPlaylistFlowUseCase

    companion object {
        @Provides
        fun provideContext(app: Application): Context =
            app.applicationContext

        @Provides
        fun provideDevicePlaylistSource(): DevicePlaylistSource =
            DevicePlaylistSource()
    }
}
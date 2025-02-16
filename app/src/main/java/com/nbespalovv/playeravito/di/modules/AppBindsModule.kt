package com.nbespalovv.playeravito.di.modules

import android.app.Application
import android.content.Context
import com.nbespalovv.playeravito.data.device.DevicePlaylistSource
import com.nbespalovv.playeravito.data.repository.DeezerTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.LocalTracksRepositoryImpl
import com.nbespalovv.playeravito.data.repository.TracksRepository
import com.nbespalovv.playeravito.domain.loadChartsUseCase.LoadChartsUseCase
import com.nbespalovv.playeravito.domain.loadChartsUseCase.LoadChartsUseCaseImpl
import com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase.LocalPlaylistFlowUseCase
import com.nbespalovv.playeravito.domain.localPlaylistFlowUseCase.LocalPlaylistFlowUseCaseImpl
import com.nbespalovv.playeravito.domain.localTracksUseCase.LocalTracksUseCase
import com.nbespalovv.playeravito.domain.localTracksUseCase.LocalTracksUseCaseImpl
import com.nbespalovv.playeravito.domain.playlistFlowUseCase.PlaylistFlowUseCase
import com.nbespalovv.playeravito.domain.playlistFlowUseCase.PlaylistFlowUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
internal interface AppBindsModule {
    @Binds
    @Singleton
    @Named(DeezerTracksRepositoryImpl.NAME_KEY)
    fun bindTracksRepository(
        repository: DeezerTracksRepositoryImpl
    ): TracksRepository

    @Binds
    @Singleton
    @Named(LocalTracksRepositoryImpl.NAME_KEY)
    fun bindLocalTracksRepository(
        repository: LocalTracksRepositoryImpl
    ) : TracksRepository

    @Binds
    fun bindPlaylistFlowUseCase(impl: PlaylistFlowUseCaseImpl): PlaylistFlowUseCase

    @Binds
    fun bindLoadChartsUseCase(impl: LoadChartsUseCaseImpl): LoadChartsUseCase

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
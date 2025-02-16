package com.nbespalovv.playeravito.di

import android.app.Application
import com.nbespalovv.playeravito.di.modules.AppModule
import com.nbespalovv.playeravito.presenter.list.deezer.DeezerPlaylistFragment
import com.nbespalovv.playeravito.presenter.list.local.LocalPlaylistFragment
import com.nbespalovv.playeravito.presenter.player.PlayerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: DeezerPlaylistFragment)
    fun inject(fragment: LocalPlaylistFragment)
    fun inject(fragment: PlayerFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
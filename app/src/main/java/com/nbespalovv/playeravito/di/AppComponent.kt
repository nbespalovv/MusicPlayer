package com.nbespalovv.playeravito.di

import android.app.Application
import com.nbespalovv.playeravito.di.modules.AppModule
import com.nbespalovv.playeravito.presenter.list.local.LocalPlaylistFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(fragment: LocalPlaylistFragment)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}
package com.nbespalovv.playeravito.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nbespalovv.playeravito.di.ViewModelFactory
import com.nbespalovv.playeravito.di.ViewModelKey
import com.nbespalovv.playeravito.presenter.list.local.LocalPlaylistViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LocalPlaylistViewModel::class)
    fun bindLocalPlaylistViewModel(model: LocalPlaylistViewModel): ViewModel
}
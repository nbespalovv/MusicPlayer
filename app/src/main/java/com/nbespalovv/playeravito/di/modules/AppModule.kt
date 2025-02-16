package com.nbespalovv.playeravito.di.modules

import dagger.Module

@Module(
    includes = [
        AppBindsModule::class,
        ViewModelModule::class,
    ]
)
interface AppModule
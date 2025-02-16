package com.nbespalovv.playeravito.di.modules

import dagger.Module

@Module(
    includes = [
        AppBindsModule::class,
        ViewModelModule::class,
        InterceptorsModule::class,
        NetworkModule::class,
    ]
)
interface AppModule
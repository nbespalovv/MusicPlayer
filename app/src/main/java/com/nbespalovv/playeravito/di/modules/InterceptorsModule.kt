package com.nbespalovv.playeravito.di.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
interface InterceptorsModule {
    @Multibinds
    fun bindInterceptors(): Set<Interceptor>

    companion object {
        @IntoSet
        @Provides
        fun provideHttpLoggingInterceptors(): Interceptor =
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }

        @IntoSet
        @Provides
        fun provideChuckerInterceptor(context: Context): Interceptor =
            ChuckerInterceptor(context)
    }
}
package com.nbespalovv.playeravito.di.modules

import com.nbespalovv.playeravito.data.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    @Provides
    fun provideApiService(client: OkHttpClient) : ApiService =
        Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    @Provides
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                interceptors.forEach {  interceptor ->
                    addInterceptor(interceptor)
                }
            }
            .build()
    }

}
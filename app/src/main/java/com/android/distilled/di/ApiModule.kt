package com.android.distilled.di

import com.android.distilled.network.DistilledApis
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {
    private val baseUrl = "https://api.themoviedb.org/3/"

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providesStatesApi(retrofit: Retrofit): DistilledApis {
        return retrofit.create(DistilledApis::class.java)
    }
}
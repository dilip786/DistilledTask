package com.android.distilled.di

import com.android.distilled.database.TvShowsDao
import com.android.distilled.network.DistilledApis
import com.android.distilled.repositories.TvShowsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesStatesRepository(weatherApis: DistilledApis, tvShowsDao: TvShowsDao): TvShowsRepository {
        return TvShowsRepository(weatherApis,tvShowsDao)
    }
}
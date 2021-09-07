package com.android.distilled.di

import android.content.Context
import androidx.room.Room
import com.android.distilled.DistilledApplication
import com.android.distilled.database.DistilledDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    private val DATABASE_NAME = "distilled_db.db"

    @Singleton
    @Provides
    fun provideAppDatabase(
    ): DistilledDatabase {
        return Room.databaseBuilder(DistilledApplication.context, DistilledDatabase::class.java, DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun providePanelsDao(db: DistilledDatabase) = db.getTvShowsDao()

}
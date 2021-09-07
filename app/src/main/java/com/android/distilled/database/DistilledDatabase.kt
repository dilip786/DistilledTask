package com.android.distilled.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.distilled.database.TvShowsDao

@Database(entities = [TvShowEntity::class], version = 1)
@TypeConverters(value = [BaseTypeConverter::class])
abstract class DistilledDatabase : RoomDatabase() {
    abstract fun getTvShowsDao() : TvShowsDao
}
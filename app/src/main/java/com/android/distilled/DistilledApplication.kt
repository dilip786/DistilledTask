package com.android.distilled

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.distilled.database.DistilledDatabase


class DistilledApplication : Application() {
    lateinit var database : RoomDatabase
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this,DistilledDatabase::class.java,"TvShows_Db").allowMainThreadQueries().build()
        application = this
    }

    companion object {
        var application: Application? = null
            private set
        val context: Context
            get() = application!!.applicationContext
    }
}
package com.android.distilled

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.distilled.database.DistilledDatabase
import com.android.distilled.database.TvShowsDao
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: DistilledDatabase
    lateinit var tvShowsDao: TvShowsDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, DistilledDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        tvShowsDao = database.getTvShowsDao()
    }

    @After
    fun close() {
        database.close()
    }
}
package com.android.distilled

import com.android.distilled.database.TvShowEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TvShowsDaoTest : BaseDaoTest() {

    @Test
    fun insertingSingleUserDataTest() {
        tvShowsDao.insert(getTestObject())
        Assert.assertEquals(true, tvShowsDao.getTvShowsListOnce().isNotEmpty())
    }

    @Test
    fun deletingSingleUserDataTest() {
        val testObj = getTestObject()
        tvShowsDao.insert(testObj)
        tvShowsDao.delete(testObj)
        Assert.assertEquals(true, tvShowsDao.getTvShowsListOnce().isEmpty())
    }

    private fun getTestObject(): TvShowEntity {
        return TvShowEntity(
            backdropPath = "/7q448EVOnuE3gVAx24krzO7SNXM.jpg",
            firstAirDate = "2021-09-03",
            genreIds = mutableListOf<Int>(1, 3),
            id = 2020200,
            name = "The D'Amelio Show",
            originCountry = mutableListOf("US"),
            originalLanguage = "US",
            originalName = "The D'Amelia Show",
            overview = "From relative obscurity and a seemingly normal life, to overnight success and thrust into the Hollywood limelight overnight, the D’Amelios are faced with new challenges and opportunities they could not have imagined.",
            popularity = 20.00,
            posterPath = "/z0iCS5Znx7TeRwlYSd4c01Z0lFx.jpg",
            voteAverage = 45.00,
            voteCount = 100,
        )
    }
}
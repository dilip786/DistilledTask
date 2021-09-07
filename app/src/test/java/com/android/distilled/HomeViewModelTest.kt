package com.android.distilled

import com.android.distilled.network.DistilledApis
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis


class HomeViewModelTest {

    var services: DistilledApis? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
        services = retrofit.create(DistilledApis::class.java)
    }

    @Test
    fun testStatesApiTest() {
        var time: Long?
        runBlocking {
            time = measureTimeMillis {
                val responseDo = services?.getShows(
                    api_key = "25a8f80ba018b52efb64f05140f6b43c",
                    page = 1
                )
                println("showsCount: ${responseDo?.results?.size ?: 0}")
                Assert.assertEquals(true, responseDo?.results?.size ?: 0 > 0)
            }
        }
        println("Time taken: $time")
    }
}
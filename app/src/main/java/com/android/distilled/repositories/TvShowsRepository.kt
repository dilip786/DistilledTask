package com.android.distilled.repositories

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import com.android.distilled.database.TvShowEntity
import com.android.distilled.database.TvShowsDao
import com.android.distilled.network.DistilledApis
import com.android.distilled.objects.TvShowsResponseDO
import com.android.distilled.ui.BottomSheetDialog
import com.android.distilled.ui.BottomSheetDialog.SORTING_OPTIONS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowsRepository @Inject constructor(
    private val distilledApis: DistilledApis,
    private val tvShowsDao: TvShowsDao,
) {
    suspend fun fetchShows(page: Int = 1, onSuccess: () -> Unit, onError: (String) -> Unit) {
        try {
            withContext(Dispatchers.IO) {
                val responseDO = distilledApis.getShows(
                    api_key = "25a8f80ba018b52efb64f05140f6b43c",
                    page = page
                )
                tvShowsDao.insertList(convertDosToEntities(responseDO?.results))
                onSuccess.invoke()
            }
        } catch (ex: Exception) {
            onError.invoke(ex.message ?: "Unknown error")
        }
    }

    fun getTvShowsFromDb(
        sortBy: SORTING_OPTIONS,
        sortType: BottomSheetDialog.SORTING_TYPE,
    ): LiveData<List<TvShowEntity>> {
        return tvShowsDao.getShowsEntities(query = SimpleSQLiteQuery(when (sortBy) {
            SORTING_OPTIONS.CLEAR -> "SELECT * FROM tvShows"
            else -> "SELECT * FROM tvShows ORDER BY ${
                when (sortBy) {
                    SORTING_OPTIONS.ALPHABETICAL -> "name"
                    else -> "vote_average"
                }
            } ${
                when (sortType) {
                    BottomSheetDialog.SORTING_TYPE.ASC -> "ASC"
                    else -> "DESC"
                }
            }"
        }))
    }

    /**
     * We can do in a better way by maintaining a adapter (Which has all entities converters) class.
     */
    private fun convertDosToEntities(results: List<TvShowsResponseDO.TvShowDo?>?): MutableList<TvShowEntity> {
        val entitiesList: MutableList<TvShowEntity> = mutableListOf()
        results?.forEach {
            entitiesList.add(
                TvShowEntity(
                    backdropPath = it?.backdropPath ?: "",
                    firstAirDate = it?.firstAirDate ?: "",
                    genreIds = it?.genreIds ?: mutableListOf(),
                    id = it?.id ?: 0,
                    name = it?.name ?: "",
                    originCountry = it?.originCountry ?: mutableListOf(),
                    originalLanguage = it?.originalLanguage ?: "",
                    originalName = it?.originalName ?: "",
                    overview = it?.overview ?: "",
                    popularity = it?.popularity ?: 0.00,
                    voteAverage = it?.voteAverage ?: 0.00,
                    posterPath = it?.posterPath ?: "",
                    voteCount = it?.voteCount ?: 0,
                )
            )
        }
        return entitiesList
    }
}
package com.android.distilled.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.distilled.database.TvShowEntity
import com.android.distilled.repositories.TvShowsRepository
import com.android.distilled.ui.BottomSheetDialog.*
import com.android.distilled.utils.NetworkUtils
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
) : ViewModel() {

    private val mError = MutableLiveData<Errors>()
    val handleError: MutableLiveData<Errors> get() = mError

    private val mShows = MutableLiveData<MutableList<TvShowEntity>>()
    val handleData: MutableLiveData<MutableList<TvShowEntity>> get() = mShows

    init {
        getTvShowsFromDb()
        fetchTvShowsDataFromServer()
    }

    fun getTvShowsFromDb(
        sortBy: SORTING_OPTIONS = SORTING_OPTIONS.CLEAR,
        sortType: SORTING_TYPE = SORTING_TYPE.ASC,
    ) {
        tvShowsRepository.getTvShowsFromDb(sortBy,sortType).observeForever {
            if (it.isEmpty()) {
                mError.value = Errors.ERROR_EMPTY_LIST
                return@observeForever
            }
            mShows.value = it.toMutableList()
        }
    }

    private fun fetchTvShowsDataFromServer() {
        if (!NetworkUtils().isNetworkAvailable()) {
            return
        }
        viewModelScope.launch {
            tvShowsRepository.fetchShows(
                onSuccess = {
                    Log.e("DISTILLED", "SUCCESS")
                },
                onError = {
                    mError.value = Errors.ERROR_SERVER_ERROR
                    Log.e("DISTILLED", it)
                }
            )
        }
    }

    enum class Errors {
        ERROR_NO_INTERNET,
        ERROR_SERVER_ERROR,
        ERROR_EMPTY_LIST
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
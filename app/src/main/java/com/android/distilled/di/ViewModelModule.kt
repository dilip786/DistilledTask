package com.android.distilled.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.distilled.viewmodels.HomeViewModel
import com.android.distilled.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @Provides
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun stateListViewModel(viewModel: HomeViewModel): ViewModel {
        return viewModel
    }
}
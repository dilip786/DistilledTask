package com.android.distilled.di

import com.android.distilled.ui.ShowsActivity
import com.android.distilled.viewmodels.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class,DatabaseModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(statesListActivity: ShowsActivity)
}
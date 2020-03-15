package com.pyteam.vividic

import android.app.Application
import com.pyteam.vividic.datasource.remote.ApiConnection
import com.pyteam.vividic.repository.MovieRepository
import com.pyteam.vividic.repository.TvShowRepository
import com.pyteam.vividic.viewmodel.MainViewModel
import com.pyteam.vividic.viewmodel.MovieDetailsViewModel
import com.pyteam.vividic.viewmodel.TvShowDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    private val networkModule = module {
        single { ApiConnection(applicationContext) }
    }
    private val repositoryModule = module {
        single { MovieRepository(applicationContext,get()) }
        single { TvShowRepository(applicationContext,get()) }
    }
    private val viewModelModule = module {
        viewModel {
            MainViewModel(get(),get())
        }
        viewModel { (movieId: Int) ->
            MovieDetailsViewModel(get(),movieId)
        }
        viewModel { (tvId: Int) ->
            TvShowDetailsViewModel(get(),tvId)
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(networkModule,repositoryModule,viewModelModule))
        }
    }

}
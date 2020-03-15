package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.MovieRepository
import com.pyteam.vividic.repository.TvShowRepository

class MainViewModel(
    movieRepository: MovieRepository,
    tvShowRepository:TvShowRepository
) : ViewModel() {

    val moviesNowPlaying = movieRepository.getNowPlaying()
    val moviesPopular = movieRepository.getPopular()
    val tvShowsOnTheAir = tvShowRepository.getOnTheAir()
    val tvShowsPopular = tvShowRepository.getPopular()
}

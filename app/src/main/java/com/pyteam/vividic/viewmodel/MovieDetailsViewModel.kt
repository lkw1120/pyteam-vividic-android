package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.MovieRepository

class MovieDetailsViewModel(
    movieRepository: MovieRepository,
    movieId:Int
) : ViewModel() {

    val details = movieRepository.getDetails(movieId)
    val credits = movieRepository.getCredits(movieId)
}

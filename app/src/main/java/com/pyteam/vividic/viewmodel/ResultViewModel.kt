package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.MovieRepository
import com.pyteam.vividic.repository.PersonRepository
import com.pyteam.vividic.repository.TvShowRepository

class ResultViewModel(
    movieRepository: MovieRepository,
    tvShowRepository: TvShowRepository,
    personRepository: PersonRepository,
    query: String
) : ViewModel() {

    val movieResult = movieRepository.searchMovie(query)
    val tvShowResult = tvShowRepository.searchTvShow(query)
    val title = "$query 검색 결과"

}
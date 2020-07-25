package com.pyteam.vividic.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pyteam.vividic.datasource.entity.movies.credits.Cast
import com.pyteam.vividic.datasource.entity.movies.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.details.Detail
import com.pyteam.vividic.repository.MovieRepository

class MovieDetailsViewModel(
    movieRepository: MovieRepository,
    val movieId: String
) : ViewModel() {

    private val details = movieRepository.getDetails(movieId)

    val credits = movieRepository.getCredits(movieId)
    val reviews = movieRepository.getReviews(movieId)
    val similar = movieRepository.getSimilar(movieId)

    val title = Transformations.map(details) { getTitle(it) }
    val releaseDate = Transformations.map(details) { getReleaseDate(it) }
    val posterPath = Transformations.map(details) { getPosterPath(it) }
    val backdropPath = Transformations.map(details) { getBackdropPath(it) }
    val genres = Transformations.map(details) { getGenres(it) }
    val rating = Transformations.map(details) { getRating(it) }
    val runtime = Transformations.map(details) { getRuntime(it) }
    val tagline = Transformations.map(details) { getTagline(it) }
    val overview = Transformations.map(details) { getOverview(it) }

    val director = Transformations.map(credits) { getDirector(it) }


    private fun getTagline(details: Detail): String {
        return details.tagline
    }

    private fun getOverview(details: Detail): String {
        return details.overview
    }

    private fun getBackdropPath(details: Detail): String {
        return details.backdropPath
    }

    private fun getPosterPath(details: Detail): String {
        return details.posterPath
    }

    private fun getReleaseDate(details: Detail): String {
        return details.releaseDate
    }

    private fun getTitle(details: Detail): String {
        return details.title
    }

    private fun getRuntime(details: Detail): String {
        val hour = Integer.parseInt(details.runtime)/60
        val min = Integer.parseInt(details.runtime)%60
        return "${hour}h ${min}m"
    }

    private fun getRating(details: Detail): String {
        return "${details.voteAverage} (${details.voteCount})"
    }

    private fun getGenres(details: Detail): String {
        val list = mutableListOf<String>()
        details.genres.forEach {
            list.add(it.name)
        }
        val str = list.toString()
        return str.substring(1,str.length-1)
    }

    private fun getDirector(credits: Credit): String {
        val list = mutableListOf<String>()
        credits.crew.forEach {
            if(it.job == "Director") {
                list.add(it.name)
            }
        }
        val str = list.toString()
        return str.substring(1,str.length-1)
    }
}

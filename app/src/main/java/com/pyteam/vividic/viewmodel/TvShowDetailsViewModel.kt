package com.pyteam.vividic.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pyteam.vividic.datasource.entity.common.credits.Credit
import com.pyteam.vividic.datasource.entity.tvshows.details.Detail
import com.pyteam.vividic.datasource.entity.tvshows.details.Seasons
import com.pyteam.vividic.repository.TvShowRepository

class TvShowDetailsViewModel(
    tvShowRepository: TvShowRepository,
    val tvId: String
) : ViewModel() {

    val details = tvShowRepository.getDetails(tvId)
    val credits = tvShowRepository.getCredits(tvId)

    val reviews = tvShowRepository.getReviews(tvId)
    val similar = tvShowRepository.getSimilar(tvId)

    val name = Transformations.map(details) { getName(it) }
    val firstAirDate = Transformations.map(details) { getFirstAirDate(it) }
    val lastAirDate = Transformations.map(details) { getLastAirDate(it) }
    val posterPath = Transformations.map(details) { getPosterPath(it) }
    val backdropPath = Transformations.map(details) { getBackdropPath(it) }
    val genres = Transformations.map(details) { getGenres(it) }
    val rating = Transformations.map(details) { getRating(it) }
    val runtime = Transformations.map(details) { getRuntime(it) }
    val overview = Transformations.map(details) { getOverview(it) }
    val seasons = Transformations.map(details) { getSeasons(it) }
    val networks = Transformations.map(details) { getNetworks(it) }
    val numberOfSeasons = Transformations.map(details) { getNumberOfSeasons(it) }

    val director = Transformations.map(credits) { getDirector(it) }

    private fun getNumberOfSeasons(details: Detail): String {
        return "${details.numberOfSeasons} Seasons"
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

    private fun getFirstAirDate(details: Detail): String {
        return details.firstAirDate
    }

    private fun getLastAirDate(details: Detail): String {
        return details.lastAirDate
    }

    private fun getName(details: Detail): String {
        return details.name
    }

    private fun getRuntime(details: Detail): String {
        return "${details.episodeRunTime.first()}m"
    }

    private fun getRating(details: Detail): String {
        return "${details.voteAverage}★ (${details.voteCount})"
    }

    private fun getGenres(details: Detail): String {
        val list = mutableListOf<String>()
        details.genres.forEach {
            list.add(it.name)
        }
        val str = list.toString()
        return str.substring(1,str.length-1)
    }

    private fun getNetworks(details: Detail): String {
        val list = mutableListOf<String>()
        details.networks.forEach {
            list.add(it.name)
        }
        val str = list.toString()
        return str.substring(1,str.length-1)
    }

    private fun getSeasons(details: Detail): List<Seasons> {
        return details.seasons
    }

    private fun getDirector(credits: Credit): String {
        val list = mutableListOf<String>()
        credits.crews.forEach {
            if(it.job == "Director") {
                list.add(it.name)
            }
        }
        val str = list.toString()
        return str.substring(1,str.length-1)
    }
}


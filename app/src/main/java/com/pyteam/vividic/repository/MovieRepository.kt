package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.movies.MovieResult
import com.pyteam.vividic.datasource.entity.movies.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.details.Detail
import com.pyteam.vividic.datasource.remote.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    context: Context,
    private val movieService: MovieService
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(movieId: String): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        val disposable =
            movieService.getDetails(movieId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie::postValue)
        return movie
    }

    fun getCredits(movieId: String): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val disposable =
            movieService.getCredits(movieId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credit::postValue)
        return credit
    }

    fun getNowPlaying(): LiveData<MovieResult> {
        val nowPlaying = MutableLiveData<MovieResult>()
        val disposable =
            movieService.getNowPlaying(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(nowPlaying::postValue)
        return nowPlaying
    }

    fun getPopular(): LiveData<MovieResult> {
        val popular = MutableLiveData<MovieResult>()
        val disposable =
            movieService.getPopular(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popular::postValue)
        return popular
    }

    fun searchMovie(query: String): LiveData<MovieResult> {
        val movie = MutableLiveData<MovieResult>()
        val disposable =
            movieService.searchMovie(API_KEY,query,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie::postValue)
        return movie
    }

}
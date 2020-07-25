package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.common.reviews.ReviewList
import com.pyteam.vividic.datasource.entity.common.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.MovieList
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

    fun getReviews(movieId: String): LiveData<ReviewList> {
        val reviewList = MutableLiveData<ReviewList>()
        val disposable =
            movieService.getReviews(movieId, API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewList::postValue)
        return reviewList
    }

    fun getSimilar(movieId: String): LiveData<MovieList> {
        val movieList = MutableLiveData<MovieList>()
        val disposable =
            movieService.getSimilar(movieId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList::postValue)
        return movieList
    }

    fun getNowPlaying(): LiveData<MovieList> {
        val movieList = MutableLiveData<MovieList>()
        val disposable =
            movieService.getNowPlaying(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList::postValue)
        return movieList
    }

    fun getPopular(): LiveData<MovieList> {
        val movieList = MutableLiveData<MovieList>()
        val disposable =
            movieService.getPopular(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList::postValue)
        return movieList
    }

    fun searchMovie(query: String): LiveData<MovieList> {
        val movieList = MutableLiveData<MovieList>()
        val disposable =
            movieService.searchMovie(API_KEY,query,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList::postValue)
        return movieList
    }

}
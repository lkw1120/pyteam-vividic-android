package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.remote.ApiConnection
import com.pyteam.vividic.datasource.entity.movies.credits.Credit
import com.pyteam.vividic.datasource.entity.movies.details.Detail
import com.pyteam.vividic.datasource.entity.movies.nowplaying.NowPlaying
import com.pyteam.vividic.datasource.entity.movies.popular.Popular
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    context: Context,
    private val apiConnection: ApiConnection
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(movieId: Int): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        val disposable = apiConnection.getMovies()
            .getDetails(movieId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie::postValue)
        return movie
    }

    fun getCredits(movieId: Int): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val disposable = apiConnection.getMovies()
            .getCredits(movieId, API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(credit::postValue)
        return credit
    }

    fun getNowPlaying(): LiveData<NowPlaying> {
        val nowPlaying = MutableLiveData<NowPlaying>()
        val disposable = apiConnection.getMovies()
            .getNowPlaying(API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(nowPlaying::postValue)
        return nowPlaying
    }

    fun getPopular(): LiveData<Popular> {
        val popular = MutableLiveData<Popular>()
        val disposable = apiConnection.getMovies()
            .getPopular(API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(popular::postValue)
        return popular
    }
}
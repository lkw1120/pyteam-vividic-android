package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.remote.ApiConnection
import com.pyteam.vividic.datasource.entity.tvshows.credits.Credit
import com.pyteam.vividic.datasource.entity.tvshows.details.Detail
import com.pyteam.vividic.datasource.entity.tvshows.ontheair.OnTheAir
import com.pyteam.vividic.datasource.entity.tvshows.popular.Popular
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TvShowRepository(
    context: Context,
    private val apiConnection: ApiConnection
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(tvId: Int): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        val disposable = apiConnection.getTvShows()
            .getDetails(tvId, API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(movie::postValue)
        return movie
    }

    fun getCredits(tvId: Int): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val disposable = apiConnection.getTvShows()
            .getCredits(tvId, API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(credit::postValue)
        return credit
    }

    fun getOnTheAir(): LiveData<OnTheAir> {
        val onTheAir = MutableLiveData<OnTheAir>()
        val disposable = apiConnection.getTvShows()
            .getOnTheAir(API_KEY,"ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onTheAir::postValue)
        return onTheAir
    }

    fun getPopular(): LiveData<Popular> {
        val popular = MutableLiveData<Popular>()
        val disposable = apiConnection.getTvShows()
            .getPopular(API_KEY, "ko-KR")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(popular::postValue)
        return popular
    }
}
package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.tvshows.TvShowResult
import com.pyteam.vividic.datasource.entity.tvshows.credits.Credit
import com.pyteam.vividic.datasource.entity.tvshows.details.Detail
import com.pyteam.vividic.datasource.remote.TvShowService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TvShowRepository(
    context: Context,
    private val tvShowService: TvShowService
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(tvId: String): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        val disposable =
            tvShowService.getDetails(tvId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie::postValue)
        return movie
    }

    fun getCredits(tvId: String): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        val disposable =
            tvShowService.getCredits(tvId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credit::postValue)
        return credit
    }

    fun getOnTheAir(): LiveData<TvShowResult> {
        val onTheAir = MutableLiveData<TvShowResult>()
        val disposable =
            tvShowService.getOnTheAir(API_KEY,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onTheAir::postValue)
        return onTheAir
    }

    fun getPopular(): LiveData<TvShowResult> {
        val popular = MutableLiveData<TvShowResult>()
        val disposable =
            tvShowService.getPopular(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(popular::postValue)
        return popular
    }

    fun searchTvShow(query: String): LiveData<TvShowResult> {
        val tvShow = MutableLiveData<TvShowResult>()
        val disposable =
            tvShowService.searchTvShow(API_KEY,query,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShow::postValue)
        return tvShow
    }


}
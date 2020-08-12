package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.people.details.Detail
import com.pyteam.vividic.datasource.entity.people.movies.MovieCredit
import com.pyteam.vividic.datasource.entity.people.tvshows.TvShowCredit
import com.pyteam.vividic.datasource.remote.PersonService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonRepository (
    context: Context,
    private val personService: PersonService
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(personId: String): LiveData<Detail> {
        val person = MutableLiveData<Detail>()
        val disposable =
            personService.getDetails(personId, API_KEY, "en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(person::postValue)
        return person
    }

    fun getMovieCredit(personId: String): LiveData<MovieCredit> {
        val credit = MutableLiveData<MovieCredit>()
        val disposable =
            personService.getMovieCredit(personId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credit::postValue)
        return credit
    }

    fun getTvShowCredit(personId: String): LiveData<TvShowCredit> {
        val credit = MutableLiveData<TvShowCredit>()
        val disposable =
            personService.getTvShowCredit(personId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credit::postValue)
        return credit
    }
}
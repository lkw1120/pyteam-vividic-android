package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pyteam.vividic.datasource.remote.PersonService
import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.people.details.Detail
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
}
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

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowRepository(
    context: Context,
    private val apiConnection: ApiConnection
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(tvId: Int): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        apiConnection.getTvShows().getDetails(tvId, API_KEY, "ko-KR")
            .enqueue(object: Callback<Detail> {
                override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                    if(response.isSuccessful) {
                        movie.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Detail>, t: Throwable) {

                }
            })
        return movie
    }

    fun getCredits(tvId: Int): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        apiConnection.getTvShows().getCredits(tvId, API_KEY, "ko-KR")
            .enqueue(object: Callback<Credit> {
                override fun onResponse(call: Call<Credit>, response: Response<Credit>) {
                    if(response.isSuccessful) {
                        credit.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Credit>, t: Throwable) {

                }
            })
        return credit
    }

    fun getOnTheAir(): LiveData<OnTheAir> {
        val onTheAir = MutableLiveData<OnTheAir>()
        apiConnection.getTvShows().getOnTheAir(API_KEY,"ko-KR")
            .enqueue(object: Callback<OnTheAir> {
                override fun onResponse(call: Call<OnTheAir>, response: Response<OnTheAir>) {
                    if(response.isSuccessful) {
                        onTheAir.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<OnTheAir>, t: Throwable) {

                }
            })
        return onTheAir
    }

    fun getPopular(): LiveData<Popular> {
        val popular = MutableLiveData<Popular>()
        apiConnection.getTvShows().getPopular(API_KEY, "ko-KR")
            .enqueue(object: Callback<Popular> {
                override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                    if(response.isSuccessful) {
                        popular.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<Popular>, t: Throwable) {

                }
            })
        return popular
    }
}
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

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    context: Context,
    private val apiConnection: ApiConnection
) {

    private val API_KEY: String = context.getString(R.string.tmdb_api_key)

    fun getDetails(movieId: Int): LiveData<Detail> {
        val movie = MutableLiveData<Detail>()
        apiConnection.getMovies().getDetails(movieId, API_KEY, "ko-KR")
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

    fun getCredits(movieId: Int): LiveData<Credit> {
        val credit = MutableLiveData<Credit>()
        apiConnection.getMovies().getCredits(movieId, API_KEY, "ko-KR")
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

    fun getNowPlaying(): LiveData<NowPlaying> {
        val nowPlaying = MutableLiveData<NowPlaying>()
        apiConnection.getMovies().getNowPlaying(API_KEY, "ko-KR")
            .enqueue(object: Callback<NowPlaying> {
                override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                    if(response.isSuccessful) {
                        nowPlaying.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<NowPlaying>, t: Throwable) {

                }
            })
        return nowPlaying
    }

    fun getPopular(): LiveData<Popular> {
        val popular = MutableLiveData<Popular>()
        apiConnection.getMovies().getPopular(API_KEY, "ko-KR")
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
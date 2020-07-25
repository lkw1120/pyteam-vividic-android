package com.pyteam.vividic.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.pyteam.vividic.R
import com.pyteam.vividic.datasource.entity.common.credits.Credit
import com.pyteam.vividic.datasource.entity.tvshows.TvShowList
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
        val detail = MutableLiveData<Detail>()
        val disposable =
            tvShowService.getDetails(tvId, API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(detail::postValue)
        return detail
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

    fun getOnTheAir(): LiveData<TvShowList> {
        val tvShowList = MutableLiveData<TvShowList>()
        val disposable =
            tvShowService.getOnTheAir(API_KEY,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowList::postValue)
        return tvShowList
    }

    fun getPopular(): LiveData<TvShowList> {
        val tvShowList = MutableLiveData<TvShowList>()
        val disposable =
            tvShowService.getPopular(API_KEY, "ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowList::postValue)
        return tvShowList
    }

    fun searchTvShow(query: String): LiveData<TvShowList> {
        val tvShowList = MutableLiveData<TvShowList>()
        val disposable =
            tvShowService.searchTvShow(API_KEY,query,"ko-KR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowList::postValue)
        return tvShowList
    }

}
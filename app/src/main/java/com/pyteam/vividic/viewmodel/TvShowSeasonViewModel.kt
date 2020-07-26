package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.TvShowRepository

class TvShowSeasonViewModel (
    tvShowRepository: TvShowRepository,
    val tvId: String,
    val seasonNumber: String
) : ViewModel() {

    val season = tvShowRepository.getSeason(tvId,seasonNumber)


}
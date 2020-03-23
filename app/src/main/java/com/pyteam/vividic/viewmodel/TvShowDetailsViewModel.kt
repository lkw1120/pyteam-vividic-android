package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.TvShowRepository

class TvShowDetailsViewModel(
    tvShowRepository: TvShowRepository,
    tvId: String
) : ViewModel() {

    val details = tvShowRepository.getDetails(tvId)
    val credits = tvShowRepository.getCredits(tvId)
}


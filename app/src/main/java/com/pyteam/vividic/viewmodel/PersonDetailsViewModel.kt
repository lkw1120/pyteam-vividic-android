package com.pyteam.vividic.viewmodel

import androidx.lifecycle.ViewModel
import com.pyteam.vividic.repository.PersonRepository

class PersonDetailsViewModel(
    personRepository: PersonRepository,
    personId: String
) : ViewModel() {

    val person = personRepository.getDetails(personId)
}

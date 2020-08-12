package com.pyteam.vividic.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pyteam.vividic.datasource.entity.people.details.Detail
import com.pyteam.vividic.repository.PersonRepository

class PersonDetailsViewModel(
    personRepository: PersonRepository,
    val personId: String
) : ViewModel() {

    private val person = personRepository.getDetails(personId)

    val movieCredit = personRepository.getMovieCredit(personId)
    val tvShowCredit = personRepository.getTvShowCredit(personId)

    val name = Transformations.map(person) { getName(it) }
    val profilePath = Transformations.map(person) { getProfilePath(it) }
    val birthday = Transformations.map(person) { getBirthday(it) }
    val deathday = Transformations.map(person) { getDeathday(it) }
    val gender = Transformations.map(person) { getGender(it) }
    val popularity = Transformations.map(person) { getPopularity(it) }
    val biography = Transformations.map(person) { getBiography(it) }


    private fun getName(person: Detail): String {
        return person.name
    }

    private fun getProfilePath(person: Detail): String {
        return person.profilePath
    }

    private fun getBirthday(person: Detail): String {
        return person.birthday
    }

    private fun getDeathday(person: Detail): String {
        return person.deathday
    }

    private fun getGender(person: Detail): String {
        return when(person.gender) {
            "1" -> "여성"
            "2" -> "남성"
            else -> "비공개"
        }
    }

    private fun getPopularity(person: Detail): String {
        return "${person.popularity}★"
    }

    private fun getBiography(person: Detail): String {
        return person.biography
    }

}

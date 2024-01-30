package com.example.characterapp.di.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.characterapp.data.model.CharactersResponse
import com.example.characterapp.data.repository.CharactersRepository
import com.example.characterapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
): ViewModel() {

    init {
            getCharacters()
        }

    private val response: LiveData<Resource<CharactersResponse>> =
        charactersRepository.getCharactersResponse()

    fun getCharacters(){
        charactersRepository.getCharacters()
    }


    fun getCharactersresponse(): LiveData<Resource<CharactersResponse>> = response

}
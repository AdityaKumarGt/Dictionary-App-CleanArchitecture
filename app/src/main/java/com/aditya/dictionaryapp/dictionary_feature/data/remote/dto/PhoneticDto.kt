package com.aditya.dictionaryapp.dictionary_feature.data.remote.dto

import com.aditya.dictionaryapp.dictionary_feature.domain.model.Phonetics

data class PhoneticDto(
    val audio: String?,
    val sourceUrl: String?,
    val text: String?
)

    fun PhoneticDto.toPhonetics(): Phonetics {
        return Phonetics(
            audio = audio
        )
    }

package com.aditya.dictionaryapp.dictionary_feature.data.remote.dto

import com.aditya.dictionaryapp.dictionary_feature.data.local.entity.WordInfoEntity
import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>?,
    val phonetic: String?,
    val phonetics: List<PhoneticDto>?,
    val sourceUrls: List<String>?,
    val word: String?
){
    fun toWordInfoEntity(): WordInfoEntity{
        return WordInfoEntity(
            meanings = meanings?.map { it.toMeaning() },
            phonetic = phonetic,
            word = word,
            phonetics = phonetics?.map { it.toPhonetics() },
            sourceUrl = sourceUrls?.get(0)
        )
    }
}
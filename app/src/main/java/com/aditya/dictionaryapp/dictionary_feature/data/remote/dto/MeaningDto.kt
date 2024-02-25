package com.aditya.dictionaryapp.dictionary_feature.data.remote.dto

import com.aditya.dictionaryapp.dictionary_feature.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<Any>?,
    val definitions: List<DefinitionDto>?,
    val partOfSpeech: String?,
    val synonyms: List<String>?
){
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions?.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}
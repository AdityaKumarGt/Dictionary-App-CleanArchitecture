package com.aditya.dictionaryapp.dictionary_feature.domain.model

data class Meaning(
    val definitions: List<Definition>?,
    val partOfSpeech: String?
)

package com.aditya.dictionaryapp.dictionary_feature.domain.model

data class WordInfo(
    val meanings: List<Meaning>?,
    val phonetic: String?,
    val word: String?,
    val phonetics: List<Phonetics>?,
    val sourceUrl: String?
)

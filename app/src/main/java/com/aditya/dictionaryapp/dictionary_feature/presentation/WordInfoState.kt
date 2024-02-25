package com.aditya.dictionaryapp.dictionary_feature.presentation

import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)

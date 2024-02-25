package com.aditya.dictionaryapp.dictionary_feature.domain.repository

import com.aditya.dictionaryapp.core.util.Resource
import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}
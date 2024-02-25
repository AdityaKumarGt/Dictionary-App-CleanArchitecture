package com.aditya.dictionaryapp.dictionary_feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.dictionaryapp.dictionary_feature.domain.model.Meaning
import com.aditya.dictionaryapp.dictionary_feature.domain.model.Phonetics
import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>?,
    val phonetic: String?,
    val word: String?,
    val phonetics: List<Phonetics>?,
    val sourceUrl: String?,
    @PrimaryKey val id: Int? = null
){
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            word = word,
            phonetics = phonetics,
            sourceUrl = sourceUrl
        )
    }
}

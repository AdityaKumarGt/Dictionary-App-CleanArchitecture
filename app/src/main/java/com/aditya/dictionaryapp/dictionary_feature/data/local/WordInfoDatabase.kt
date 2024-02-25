package com.aditya.dictionaryapp.dictionary_feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aditya.dictionaryapp.dictionary_feature.data.local.entity.WordInfoEntity
import com.aditya.dictionaryapp.dictionary_feature.data.local.converters.MeaningListConverter
import com.aditya.dictionaryapp.dictionary_feature.data.local.converters.PhoneticsListConverter

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(
    MeaningListConverter::class,
    PhoneticsListConverter::class
)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val dao: WordInfoDao
}
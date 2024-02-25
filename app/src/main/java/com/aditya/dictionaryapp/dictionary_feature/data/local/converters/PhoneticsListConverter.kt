package com.aditya.dictionaryapp.dictionary_feature.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aditya.dictionaryapp.dictionary_feature.data.util.JsonParser
import com.aditya.dictionaryapp.dictionary_feature.domain.model.Meaning
import com.aditya.dictionaryapp.dictionary_feature.domain.model.Phonetics
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class PhoneticsListConverter(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromPhoneticsJson(json: String?): List<Phonetics>? {
        return json?.let {
            jsonParser.fromJson<ArrayList<Phonetics>>(
                it,
                object : TypeToken<ArrayList<Phonetics>>() {}.type
            )
        } ?: emptyList()
    }

    @TypeConverter
    fun toPhoneticsJson(list: List<Phonetics>?): String {
        return jsonParser.toJson(
            list,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}
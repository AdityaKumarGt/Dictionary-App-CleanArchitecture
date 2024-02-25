package com.aditya.dictionaryapp.dictionary_feature.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aditya.dictionaryapp.dictionary_feature.data.util.JsonParser
import com.aditya.dictionaryapp.dictionary_feature.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class MeaningListConverter(private val jsonParser: JsonParser) {
    @TypeConverter
    fun fromMeaningJson(json: String?): List<Meaning>? {
        return json?.let {
            jsonParser.fromJson<ArrayList<Meaning>>(
                it,
                object: TypeToken<ArrayList<Meaning>>(){}.type
            )
        } ?: emptyList()
    }


    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>?): String {
        return jsonParser.toJson(
            meanings,
            object: TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }
}
package com.aditya.dictionaryapp.dictionary_feature.di

import android.app.Application
import androidx.room.Room
import com.aditya.dictionaryapp.core.util.Constants
import com.aditya.dictionaryapp.dictionary_feature.data.local.WordInfoDatabase
import com.aditya.dictionaryapp.dictionary_feature.data.local.converters.MeaningListConverter
import com.aditya.dictionaryapp.dictionary_feature.data.local.converters.PhoneticsListConverter
import com.aditya.dictionaryapp.dictionary_feature.data.remote.DictionaryApi
import com.aditya.dictionaryapp.dictionary_feature.data.repository.WordInfoRepositoryImpl
import com.aditya.dictionaryapp.dictionary_feature.data.util.GsonParser
import com.aditya.dictionaryapp.dictionary_feature.domain.use_case.GetWordInfo
import com.aditya.dictionaryapp.dictionary_feature.domain.repository.WordInfoRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {
    @Provides
    @Singleton
    fun provideWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository{
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase{
        return Room.databaseBuilder(
            app, WordInfoDatabase::class.java, "word_db"
        ).addTypeConverter(MeaningListConverter(GsonParser(Gson())))
            .addTypeConverter(PhoneticsListConverter(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}
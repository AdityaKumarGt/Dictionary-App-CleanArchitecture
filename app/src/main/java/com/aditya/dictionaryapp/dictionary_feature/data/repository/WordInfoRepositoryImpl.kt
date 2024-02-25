package com.aditya.dictionaryapp.dictionary_feature.data.repository

import com.aditya.dictionaryapp.core.util.Resource
import com.aditya.dictionaryapp.dictionary_feature.data.local.WordInfoDao
import com.aditya.dictionaryapp.dictionary_feature.data.remote.DictionaryApi
import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo
import com.aditya.dictionaryapp.dictionary_feature.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

         val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
         emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            remoteWordInfos.map { it.word }?.let { dao.deleteWordInfos(it) }
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })

        } catch (e: HttpException){
            emit(Resource.Error(
                message ="Oops, something went wrong!",
                data = wordInfos
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach the server, Check your Internet connection",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}
package com.example.dictionarytutorial.feature_dictionary.data.repository

import com.example.dictionarytutorial.core.util.Resource
import com.example.dictionarytutorial.feature_dictionary.data.local.WordInfoDao
import com.example.dictionarytutorial.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo
import com.example.dictionarytutorial.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val wordDao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = wordDao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))
        try {
            val remoteWordInfos = api.getWordInfo(word)
            wordDao.deleteWordInfos(remoteWordInfos.map { it.word })
            wordDao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        }
        catch(e: HttpException) {
            emit(Resource.Error(message = "Что-то пошло не так при загрузке данных!", data = wordInfos))
        }
        catch(e: IOException) {
            emit(Resource.Error(
                message = "Не удалось получить данные по сети, проверьте свое подключение к интернету",
                data = wordInfos
            ))
        }

        val newWordInfos = wordDao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(data = newWordInfos))
    }
}
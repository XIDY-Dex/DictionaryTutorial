package com.example.dictionarytutorial.feature_dictionary.domain.repository

import com.example.dictionarytutorial.core.util.Resource
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String) : Flow<Resource<List<WordInfo>>>
}
package com.example.dictionarytutorial.feature_dictionary.data.remote.dto

import com.example.dictionarytutorial.feature_dictionary.data.local.entity.WordInfoEntity
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo

data class WordInfoDto(
    val meaningDtos: List<MeaningDto>,
    val phoneticDtos: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfo() : WordInfo {
        return WordInfo(
            meanings = meaningDtos.map { it.toMeaning() },
            sourceUrls = sourceUrls,
            word = word
        )
    }

    fun toWordInfoEntity() : WordInfoEntity {
        return WordInfoEntity(
            meanings = meaningDtos.map { it.toMeaning() },
            word = word
        )
    }
}
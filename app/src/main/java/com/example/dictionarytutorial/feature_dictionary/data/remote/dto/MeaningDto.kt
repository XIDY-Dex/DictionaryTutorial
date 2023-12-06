package com.example.dictionarytutorial.feature_dictionary.data.remote.dto

import com.example.dictionarytutorial.feature_dictionary.domain.model.Meaning

data class MeaningDto(
    val definitionDtos: List<DefinitionDto>,
    val partOfSpeech: String,
) {
    fun toMeaning() : Meaning {
        return Meaning(
            definitions = definitionDtos.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}
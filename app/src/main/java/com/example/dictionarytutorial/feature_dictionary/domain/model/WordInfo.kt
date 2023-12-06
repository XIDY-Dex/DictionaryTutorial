package com.example.dictionarytutorial.feature_dictionary.domain.model

data class WordInfo(
    val meanings: List<Meaning>,
    val sourceUrls: List<String>?,
    val word: String
)

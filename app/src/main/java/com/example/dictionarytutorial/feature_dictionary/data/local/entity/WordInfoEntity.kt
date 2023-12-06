package com.example.dictionarytutorial.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionarytutorial.feature_dictionary.domain.model.Meaning
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo

@Entity(tableName = "wordinfo")
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val meanings: List<Meaning>
) {
    fun toWordInfo() : WordInfo {
        return WordInfo(
            word = word,
            meanings = meanings,
            sourceUrls = null
        )
    }
}

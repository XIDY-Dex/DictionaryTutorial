package com.example.dictionarytutorial.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val loading: Boolean = false
)
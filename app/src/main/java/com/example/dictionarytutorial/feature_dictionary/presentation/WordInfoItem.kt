package com.example.dictionarytutorial.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionarytutorial.feature_dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = wordInfo.word, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.heightIn(16.dp))
        wordInfo.meanings.forEach { item ->
            Text(text = item.partOfSpeech, fontWeight = FontWeight.Bold)
            item.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.heightIn(16.dp))
                definition.example?.let { example ->
                    Text(text = "Пример использования: ${definition.example}")
                }
                Spacer(modifier = Modifier.heightIn(8.dp))
            }
            Spacer(modifier = Modifier.heightIn(16.dp))
        }
    }
}
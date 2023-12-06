package com.example.dictionarytutorial

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionarytutorial.feature_dictionary.presentation.WordInfoItem
import com.example.dictionarytutorial.feature_dictionary.presentation.WordInfoViewModel
import com.example.dictionarytutorial.ui.theme.DictionaryTutorialTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTutorialTheme {
                // A surface container using the 'background' color from the theme
                    val viewModel: WordInfoViewModel = hiltViewModel()
                    val state = viewModel.wordState.value
                    val snackBarHost = remember { SnackbarHostState() }

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest {event ->
                            when(event) {
                                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                    snackBarHost.showSnackbar(
                                        message = event.message
                                    )
                                }
                            }
                        }
                    }
                Scaffold(
                    snackbarHost = { SnackbarHost(snackBarHost) }
                ) {
                    Box(
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    )   {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)) {
                                    TextField(value = viewModel.searchQuery.value,
                                        onValueChange = viewModel::onSearch,
                                        modifier = Modifier.fillMaxWidth(),
                                        placeholder = { Text("Поиск...")})
                                    Spacer(modifier = Modifier.heightIn(16.dp))
                                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                                        items(state.wordInfoItems.size) { i ->
                                            val wordInfo = state.wordInfoItems[i]
                                            if(i > 0) {
                                                Spacer(modifier = Modifier.heightIn(16.dp))
                                            }
                                            WordInfoItem(wordInfo = wordInfo)
                                            if(i < state.wordInfoItems.size - 1) {
                                                Divider()
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
            }
        }
    }
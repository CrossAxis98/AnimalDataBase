package com.example.animalroom

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.animalroom.ui.theme.AnimalRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimalRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext as Application
                            )
                        )
                        ScreenSetup(viewModel)
                    }
                }
            }
        }
    }
}

@Composable fun ScreenSetup(viewModel: MainViewModel) {
    val allAnimals by viewModel.allAnimals.observeAsState(listOf())
    MainScreen(allAnimals, viewModel)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(allAnimals: List<Animal>, viewModel: MainViewModel) {
    var animalName by remember {
        mutableStateOf("")
    }
    var animalCategory by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                modifier = Modifier.padding(top = 20.dp),
                text = animalName,
                label = "Animal name",
                onTextChange = { animalName = it },
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                iconId = R.mipmap.ic_ali_foreground,
                iconModifier = Modifier.size(55.dp)
            )
            CustomTextField(
                modifier = Modifier.padding(bottom = 10.dp),
                text = animalCategory,
                label = "Animal category",
                onTextChange = { animalCategory = it },
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                iconId = R.mipmap.ic_quarium_dupa,
                iconModifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { viewModel.insertAnimal(Animal(aniamlName = animalName, animalCategory = animalCategory)) }) {
                    Text(text = "Add animal")
                }
                Button(onClick = { viewModel.deleteAnimal(animalName)}) {
                    Text(text = "Remove animal")
                }
            }
            if (allAnimals.isNotEmpty()) {
                AnimalsPresentation(allAnimals)
            }
        }
    }
}

@Composable
private fun AnimalsPresentation(allAnimals: List<Animal>) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        elevation = 4.dp,
        border = BorderStroke(
            1.dp,
            Color.LightGray
        )
    ) {
        LazyColumn {
            itemsIndexed(allAnimals) { index, it ->
                AnimalRow(it, index)
            }
        }
    }
}

@Composable
private fun AnimalRow(it: Animal, index: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = (index + 1).toString())
            Text(text = it.aniamlName)
            Text(text = it.animalCategory)
        }
    }
}
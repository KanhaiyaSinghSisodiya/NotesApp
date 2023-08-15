package com.example.notesapp.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.data.NoteItem
import com.example.notesapp.R
import com.example.notesapp.ui.theme.Blue
import com.example.notesapp.ui.theme.LightBlue

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AddNoteScreen(flagString: String, onSaveNote: () -> Unit) {
    val viewModel: AddNoteScreenViewModel = viewModel(factory = MyViewModelFactor(LocalContext.current, flagString))
    val dao = viewModel.dao
    val scope = rememberCoroutineScope()
    val note by viewModel.note.collectAsStateWithLifecycle()

    var title by remember {
        mutableStateOf("Title")
    }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = Blue,
        backgroundColor = LightBlue
    )

    var des by remember {
        mutableStateOf("Description")
    }
    if(flagString != "ADD") {
        title = note.title
        des = note.description
    }

    BackHandler(true) {
        scope.launch {
            if(flagString.equals("ADD"))    dao.upsert(NoteItem(title = title, description = des))
            else    dao.upsert(NoteItem(id = note.id, title = title, description = des))
        }
        onSaveNote()
        Log.d("jkk","jkkk")
    }

    Scaffold(
        floatingActionButton =  {
            FloatingActionButton(onClick = {
                scope.launch {
                    dao.delete(note)
                    onSaveNote()
                }
            }, containerColor = MaterialTheme.colorScheme.background) {
                Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "Delete")
            }
        },

    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
                TextField(value = title, onValueChange = {title = it}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp), colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Blue
                ),
                    textStyle = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
                )
            }
            Text(text = "August 19 16:18 Sat | 5 words", modifier = Modifier
                .padding(start = 25.dp)
                .background(MaterialTheme.colorScheme.background), fontSize = 11.sp)

            CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {

                TextField(value = des, onValueChange = {des = it}, modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp), colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Blue
                ))
            }
        }
    }
}


@Preview
@Composable
fun wi() {
    AddNoteScreen(flagString = "ADD", onSaveNote = {})
}
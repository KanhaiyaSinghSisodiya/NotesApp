package com.example.notesapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.R
import com.example.notesapp.data.NoteItem

@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier, onAddNote: (String) -> Unit) {
    Scaffold(
        floatingActionButton = {
        FloatingActionButton(
            onClick = { onAddNote("ADD") },
            shape = RoundedCornerShape(16.dp),
            containerColor = MaterialTheme.colorScheme.background,
            ) {
            Icon(painter = painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add Note")
        }
    }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()) {
            val viewModel: MainScreenViewModel = viewModel(factory = MyViewModelFactory(
                LocalContext.current))
            val state by viewModel._contacts.collectAsState()

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Notes", fontSize = 24.sp, fontWeight = FontWeight.W700)
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(text = "Edit", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.W500)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = "Search")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.round_menu_24), contentDescription = "Menu")
                    }
                }

            }
            ContactScreen(state = state, onAddNote)

        }

    }
}

@Composable
fun ContactScreen(state: List<NoteItem>, onAddNote: (String) -> Unit) {
    LazyColumn(Modifier.padding(10.dp)) {
        items(items = state, itemContent = {
            Card(elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ), modifier = Modifier
                .padding(10.dp)
                .height(65.dp)
                .clickable {
                    onAddNote(it.id.toString())
                }) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {

                    var month = when(it.dateTime.subSequence(5,7)) {
                        "01" -> "Jan"
                        "02" -> "Feb"
                        "03" -> "Mar"
                        "04" -> "Apr"
                        "05" -> "May"
                        "06" -> "Jun"
                        "07" -> "Jul"
                        "08" -> "Aug"
                        "09" -> "Sep"
                        "10" -> "Oct"
                        "11" -> "Nov"
                        "12" -> "Dec"
                        else -> "error"
                    }

                    Text(text = it.title, fontWeight = FontWeight.W700, modifier = Modifier.padding(start = 8.dp, top = 4.dp))
                    Text(text = "$month ${it.dateTime.subSequence(8,16)}", fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp, bottom = 4.dp))
                }
            }

        })
    }
}

@Preview
@Composable
fun jk() {
    
}









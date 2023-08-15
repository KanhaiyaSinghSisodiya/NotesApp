package com.example.notesapp.screens

import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.notesapp.data.NoteItem
import com.example.notesapp.data.NotesDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainScreenViewModel(context: Context): ViewModel() {
    private val dao = NotesDatabase.getDatabase(context).getNotesDao()


     val _contacts = dao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())






}
class MyViewModelFactory(private val dbname: Context) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainScreenViewModel(dbname) as T
}




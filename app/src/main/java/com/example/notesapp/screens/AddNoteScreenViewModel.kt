package com.example.notesapp.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.NoteItem
import com.example.notesapp.data.NotesDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class AddNoteScreenViewModel(context: Context, onSaveNote1: String): ViewModel() {

    val dao = NotesDatabase.getDatabase(context).getNotesDao()


    val id = if(onSaveNote1.equals("ADD"))  {
        1
    }
    else {
        onSaveNote1.toInt()
    }

    val note = dao.getItem(id).stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NoteItem(8,"NoTitle","NoDeSC"))



}

class MyViewModelFactor(private val dbname: Context, val onSaveNote1: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = AddNoteScreenViewModel(dbname, onSaveNote1) as T
}
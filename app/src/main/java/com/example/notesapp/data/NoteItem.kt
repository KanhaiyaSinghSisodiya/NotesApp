package com.example.notesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class NoteItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var title: String,
    val description: String
)
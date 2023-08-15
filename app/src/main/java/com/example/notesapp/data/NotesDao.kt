package com.example.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Upsert
    suspend fun upsert(item: NoteItem)

    @Query("SELECT * FROM NOTESTABLE")
    fun getAllItems(): Flow<List<NoteItem>>

    @Query("SELECT * from NOTESTABLE WHERE id = :id")
    fun getItem(id: Int): Flow<NoteItem>

    @Delete
    suspend fun delete(noteItem: NoteItem)

}
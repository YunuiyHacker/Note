package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository

import kotlinx.coroutines.flow.Flow
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note

    fun getAllNotes(): Flow<List<Note>>
}
package yunuiy_hacker.ryzhaya_tetenka.feature.notes.data.repository

import kotlinx.coroutines.flow.Flow
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.data.LocalDatabase
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.data.NoteDao
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) :
    NoteRepository {
    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note {
        return dao.getNoteById(id)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }
}
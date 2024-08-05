package yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.NoteUseCases
import yunuiy_hacker.ryzhaya_tetenka.util.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val noteUseCases: NoteUseCases) : ViewModel() {
    val state by mutableStateOf(MainState())

    init {
        getAllNotes()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetAllNotes -> getAllNotes()
            is MainEvent.InsertNote -> insertNote(event.note)
            is MainEvent.UpdateNote -> updateNote(event.note)
            is MainEvent.DeleteNote -> deleteNote(event.note)
            is MainEvent.UndoDeleteNote -> undoDeleteNote()
            is MainEvent.GetNoteById -> getNoteById(event.noteId)
            is MainEvent.UpdateNoteTitle -> updateNoteTitle(event.newValue)
            is MainEvent.UpdateNoteDescription -> updateNoteDescription(event.newValue)
            is MainEvent.UpdateIsBookmarked -> updateIsBookmarked(event.newValue)
        }
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            noteUseCases.getAllNotes().onStart {
                state.response.value = Response.Loading
            }.catch {
                state.response.value = Response.Error(it)
            }.collect {
                state.response.value = Response.Success(it)
            }
        }
    }

    private fun insertNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.insertNote(note)
        }
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch {
            noteUseCases.updateNote(note)
        }
    }

    private fun deleteNote(note: Note) {
        viewModelScope.launch {
            state.deletedNote = note
            noteUseCases.deleteNote(note)
        }
    }

    private fun undoDeleteNote() {
        viewModelScope.launch {
            state.deletedNote?.let { note ->
                noteUseCases.insertNote(note)
            }
        }
    }

    private fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            state.note = noteUseCases.getNoteById(noteId)
        }
    }

    private fun updateNoteTitle(newValue: String) {
        state.note = state.note.copy(title = newValue)
    }

    private fun updateNoteDescription(newValue: String) {
        state.note = state.note.copy(description = newValue)
    }

    private fun updateIsBookmarked(newValue: Boolean) {
        state.note = state.note.copy(isBookmarked = newValue)
    }
}
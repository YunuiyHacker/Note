package yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation

import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note

sealed class MainEvent() {
    data object GetAllNotes : MainEvent()
    data class InsertNote(val note: Note) : MainEvent()
    data class UpdateNote(val note: Note) : MainEvent()
    data class DeleteNote(val note: Note) : MainEvent()
    data object UndoDeleteNote : MainEvent()
    data class GetNoteById(val noteId: Int) : MainEvent()
    data class UpdateNoteTitle(val newValue: String) : MainEvent()
    data class UpdateNoteDescription(val newValue: String) : MainEvent()
    data class UpdateIsBookmarked(val newValue: Boolean) : MainEvent()
}
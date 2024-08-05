package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case

data class NoteUseCases(
    val deleteNote: DeleteNote,
    val getAllNotes: GetAllNotes,
    val getNoteById: GetNoteById,
    val insertNote: InsertNote,
    val updateNote: UpdateNote
)
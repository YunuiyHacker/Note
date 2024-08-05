package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case

import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteById @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(noteId: Int): Note {
        return repository.getNoteById(noteId)
    }
}
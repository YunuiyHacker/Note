package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case

import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject

class InsertNote @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) {
        repository.insertNote(note)
    }
}
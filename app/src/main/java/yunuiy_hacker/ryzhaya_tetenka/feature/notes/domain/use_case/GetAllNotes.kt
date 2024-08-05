package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case

import kotlinx.coroutines.flow.Flow
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotes @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
    }
}
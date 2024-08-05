package yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note
import yunuiy_hacker.ryzhaya_tetenka.util.Response
import java.text.SimpleDateFormat
import java.util.Date

class MainState {
    var note by mutableStateOf(
        Note(
            0,
            "",
            null,
            false,
            SimpleDateFormat("dd MMMM HH:mm").format(Date())
        )
    )
    var deletedNote: Note? = null
    var response = MutableStateFlow<Response<List<Note>>>(Response.Loading)
}
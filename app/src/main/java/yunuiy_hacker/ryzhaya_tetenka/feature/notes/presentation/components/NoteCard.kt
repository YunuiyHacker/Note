package yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.components

import android.view.GestureDetector
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainEvent
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainViewModel
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.DarkCardColor
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.montserrat
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.ubuntu
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onClickItem: (Int) -> Unit,
    onUndoDeleteClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    onClickItem(note.id)
                },
                onLongClick = {
                    viewModel.onEvent(MainEvent.DeleteNote(note))
                    onUndoDeleteClick()
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = DarkCardColor
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, end = 12.dp),
            text = note.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            fontFamily = montserrat,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
        if (!note.description.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(
                    horizontal = 12.dp, vertical = 4.dp
                ),
                text = note.description,
                fontFamily = montserrat,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
        Text(
            modifier = Modifier.padding(
                start = 12.dp,
                top = 4.dp,
                end = 12.dp,
                bottom = 12.dp
            ),
            text = note.date.toString(),
            fontFamily = montserrat,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Gray,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}
package yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onClickItem: (Int) -> Unit,
    onUndoDeleteClick: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier.fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(160.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(notes) { note ->
            NoteCard(modifier = Modifier.padding(bottom = if (note.id == notes.last().id) 12.dp else 0.dp),
                note = note,
                onClickItem = onClickItem,
                onUndoDeleteClick = { onUndoDeleteClick() })
        }
    }
}
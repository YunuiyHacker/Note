package yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.functions

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainEvent
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainViewModel

fun undoSnackbar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    viewModel: MainViewModel
) {
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val result = snackbarHostState.showSnackbar(
            message = "Заметка удалена",
            actionLabel = "UNDO",
            duration = SnackbarDuration.Short
        )
        when (result) {
            SnackbarResult.ActionPerformed -> {
                viewModel.onEvent(MainEvent.UndoDeleteNote)
            }
            SnackbarResult.Dismissed -> {

            }
        }
    }
}
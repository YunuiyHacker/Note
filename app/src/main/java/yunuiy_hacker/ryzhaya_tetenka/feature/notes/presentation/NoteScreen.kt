package yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.EmptyListScreen
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.LoadingAndErrorScreen
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainViewModel
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.functions.undoSnackbar
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.DarkCardColor
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.Primary
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.ubuntu
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.components.NoteList
import yunuiy_hacker.ryzhaya_tetenka.util.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    onAddNoteClick: (Int) -> Unit,
    onClickItem: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    val response by viewModel.state.response.collectAsStateWithLifecycle()

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                containerColor = DarkCardColor,
                contentColor = Color.White,
                actionColor = Primary
            )
        }
    }, topBar = {
        TopAppBar(
            title = { Text(text = "Заметки", fontFamily = ubuntu, color = Primary) },
            navigationIcon = {
                Icon(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    imageVector = Icons.AutoMirrored.Filled.StickyNote2,
                    contentDescription = "",
                    tint = Primary
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { onAddNoteClick(-1) }, containerColor = Primary, contentColor = Color.White
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }, containerColor = Color.Black) {
        AnimatedContent(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            targetState = response,
            label = "animated content",
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) togetherWith fadeOut()

            }) { result ->
            when (result) {
                is Response.Loading -> {
                    LoadingAndErrorScreen(label = "Загрузка...")
                }

                is Response.Success -> {
                    val notes = result.data
                    if (notes.isEmpty()) {
                        EmptyListScreen()
                    } else {
                        NoteList(modifier = Modifier
                            .padding(horizontal = 12.dp),
                            notes = notes,
                            onClickItem = onClickItem,
                            onUndoDeleteClick = {
                                undoSnackbar(scope, snackbarHostState, viewModel)
                            })
                    }
                }

                is Response.Error -> LoadingAndErrorScreen(
                    label = result.error.message ?: "Что-то пошло не так"
                )

                else -> Unit
            }
        }
    }
}
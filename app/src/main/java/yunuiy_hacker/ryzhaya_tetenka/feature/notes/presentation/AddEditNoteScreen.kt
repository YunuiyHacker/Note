package yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainEvent
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainViewModel
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.montserrat
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.ubuntu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    noteId: Int,
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isEditable by remember {
        mutableStateOf(false)
    }
    val focusRequest = FocusRequester()
    val textStyle = TextStyle(fontSize = 18.sp, fontFamily = montserrat)

    LaunchedEffect(true) {
        if (noteId > 0) {
            viewModel.onEvent(MainEvent.GetNoteById(noteId))
        }
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = if (noteId > 0) "Редактирование" else "Новая заметка") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                }
            }, actions = {
                IconToggleButton(checked = viewModel.state.note.isBookmarked, onCheckedChange = {
                    viewModel.onEvent(MainEvent.UpdateIsBookmarked(it))
                    viewModel.onEvent(MainEvent.UpdateNote(viewModel.state.note.copy(isBookmarked = it)))
                }) {
                    Icon(
                        imageVector = if (viewModel.state.note.isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkAdd,
                        contentDescription = ""
                    )
                }
                IconButton(onClick = {
                    if (viewModel.state.note.title.isNotBlank()) {
                        viewModel.onEvent(MainEvent.InsertNote(viewModel.state.note))
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Заголовок не может быть пустым", Toast.LENGTH_LONG)
                            .show()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "")
                }
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
        )
    }, containerColor = Color.Black) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LaunchedEffect(key1 = true) {
                if (noteId == -1) {
                    focusRequest.requestFocus()
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp)
                    .focusRequester(focusRequest),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                textStyle = textStyle.copy(fontSize = 22.sp),
                value = viewModel.state.note.title,
                onValueChange = {
                    viewModel.onEvent(MainEvent.UpdateNoteTitle(it))
                },
                placeholder = {
                    Text(
                        text = "Заголовок...",
                        style = textStyle.copy(fontSize = 22.sp)
                    )
                }
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .offset(y = -10.dp),
                text = buildAnnotatedString {
                    append(viewModel.state.note.date + "   ")
                    withStyle(style = SpanStyle(fontSize = 10.sp, color = Color.DarkGray)) {
                        append("|")
                    }
                    append("   " + if (viewModel.state.note.description.isNullOrBlank()) 0.symbols() else viewModel.state.note.description?.length?.symbols())
                },
                fontFamily = ubuntu,
                fontSize = 12.sp,
                color = Color.Gray
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 4.dp, end = 4.dp, bottom = 4.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                textStyle = textStyle.copy(fontSize = 16.sp),
                value = viewModel.state.note.description ?: "",
                onValueChange = {
                    viewModel.onEvent(MainEvent.UpdateNoteDescription(it))
                },
                placeholder = {
                    Text(
                        text = "Описание...",
                        style = textStyle.copy(fontSize = 16.sp)
                    )
                }
            )
        }
    }
}

fun Int.symbols(): String {
    val lastNumber = this.toString().takeLast(1).toInt()
    if (lastNumber == 1) return this.toString().plus(" символ")
    else if (lastNumber >= 2 && lastNumber <= 4) return this.toString().plus(" символа")
    return this.toString().plus(" символов")
}
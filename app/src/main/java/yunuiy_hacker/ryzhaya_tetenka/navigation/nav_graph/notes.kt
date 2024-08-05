package yunuiy_hacker.ryzhaya_tetenka.navigation.nav_graph

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.AddEditNoteScreen
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.NoteScreen
import yunuiy_hacker.ryzhaya_tetenka.navigation.Route
import yunuiy_hacker.ryzhaya_tetenka.navigation.Tab

fun NavGraphBuilder.notes(navController: NavController) {
    composable(route = Route.NoteScreen.route) {
        NoteScreen(onAddNoteClick = { noteId ->
            navController.navigate(route = "${Route.AddEditNoteScreen.route}/$noteId")
        }, onClickItem = { noteId ->
            navController.navigate(route = "${Route.AddEditNoteScreen.route}/$noteId")
        })
    }
    composable(
        route = "${Route.AddEditNoteScreen.route}/{noteId}",
        arguments = listOf(
            navArgument(
                "noteId"
            ) { type = NavType.IntType }
        ),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300, easing = FastOutSlowInEasing)
            )
        }
    ) { entry ->
        entry.arguments?.getInt("noteId").let { noteId ->
            AddEditNoteScreen(noteId = noteId!!, navController = navController)
        }
    }
}
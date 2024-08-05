package yunuiy_hacker.ryzhaya_tetenka.navigation.nav_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import yunuiy_hacker.ryzhaya_tetenka.feature.favorites.presentation.FavoritesScreen
import yunuiy_hacker.ryzhaya_tetenka.navigation.Route
import yunuiy_hacker.ryzhaya_tetenka.navigation.Tab

fun NavGraphBuilder.favorites(navController: NavController) {
    composable(route = Route.FavoriteScreen.route) {
        FavoritesScreen(onClickItem = { noteId ->
            navController.navigate(route = "${Route.AddEditNoteScreen.route}/$noteId")
        })
    }
}
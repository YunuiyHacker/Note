package yunuiy_hacker.ryzhaya_tetenka.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StickyNote2
import androidx.compose.material.icons.automirrored.outlined.StickyNote2
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Tab(
    val route: String, val icon: ImageVector, val selectedIcon: ImageVector, val label: String
) {
    data object Notes : Tab(
        route = Route.NoteScreen.route,
        icon = BottomAppBarIcons.homeIconOutlined,
        selectedIcon = BottomAppBarIcons.homeIconFilled,
        label = "Заметки"
    )

    data object Favorites : Tab(
        route = Route.FavoriteScreen.route,
        icon = BottomAppBarIcons.bookmarkIconOutlined,
        selectedIcon = BottomAppBarIcons.bookmarkIconFilled,
        label = "Избранное"
    )
}

private object BottomAppBarIcons {
    val homeIconOutlined = Icons.AutoMirrored.Outlined.StickyNote2
    val homeIconFilled = Icons.AutoMirrored.Filled.StickyNote2

    val bookmarkIconOutlined = Icons.Outlined.Bookmarks
    val bookmarkIconFilled = Icons.Filled.Bookmarks
}

val bottomNavBarTabs = listOf(Tab.Notes, Tab.Favorites)
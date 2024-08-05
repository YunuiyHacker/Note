package yunuiy_hacker.ryzhaya_tetenka.navigation

sealed class Route(val route: String) {
    data object NoteScreen : Route("noteScreen")
    data object FavoriteScreen : Route("favoriteScreen")
    data object AddEditNoteScreen : Route("addEditNoteScreen")
}
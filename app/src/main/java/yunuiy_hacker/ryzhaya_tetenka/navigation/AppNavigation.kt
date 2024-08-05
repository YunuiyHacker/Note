package yunuiy_hacker.ryzhaya_tetenka.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.Primary
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.ubuntu
import yunuiy_hacker.ryzhaya_tetenka.navigation.nav_graph.favorites
import yunuiy_hacker.ryzhaya_tetenka.navigation.nav_graph.notes

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val isBottomAppBarVisible = rememberSaveable(navBackStackEntry) {
        navBackStackEntry?.destination?.route == Route.NoteScreen.route || navBackStackEntry?.destination?.route == Route.FavoriteScreen.route
    }

    Scaffold(bottomBar = {
        if (isBottomAppBarVisible) {
            BottomNavigationBar(navController = navController)
        }
    }) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            navController = navController,
            startDestination = Route.NoteScreen.route
        ) {
            notes(navController = navController)
            favorites(navController = navController)
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFF131313)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavBarTabs.forEach { tab ->
            val selected = currentDestination?.hierarchy?.any {
                it.route == tab.route
            } == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) tab.selectedIcon else tab.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(
                        text = tab.label,
                        fontFamily = ubuntu,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color(0xFF131313),
                    selectedIconColor = Primary,
                    selectedTextColor = Primary
                )
            )
        }
    }
}
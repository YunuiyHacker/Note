package yunuiy_hacker.ryzhaya_tetenka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation.MainViewModel
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.NoteTheme
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.AddEditNoteScreen
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.presentation.NoteScreen
import yunuiy_hacker.ryzhaya_tetenka.navigation.AppNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            NoteTheme {
                val isSystemDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Black,
                        darkIcons = !isSystemDarkMode
                    )
                }

                AppNavigation()
            }
        }
    }
}
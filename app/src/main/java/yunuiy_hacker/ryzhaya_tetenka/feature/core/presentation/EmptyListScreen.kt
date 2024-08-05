package yunuiy_hacker.ryzhaya_tetenka.feature.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import yunuiy_hacker.ryzhaya_tetenka.feature.core.ui.theme.ubuntu

@Composable
fun EmptyListScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Нет записей", fontFamily = ubuntu, fontSize = 22.sp)
    }
}
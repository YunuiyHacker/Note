package yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String?,
    val isBookmarked: Boolean = false,
    val date: String
)

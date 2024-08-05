package yunuiy_hacker.ryzhaya_tetenka.feature.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
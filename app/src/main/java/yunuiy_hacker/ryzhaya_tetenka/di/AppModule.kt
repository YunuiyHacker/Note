package yunuiy_hacker.ryzhaya_tetenka.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.data.LocalDatabase
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.data.repository.NoteRepositoryImpl
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.repository.NoteRepository
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.DeleteNote
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.GetAllNotes
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.GetNoteById
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.InsertNote
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.NoteUseCases
import yunuiy_hacker.ryzhaya_tetenka.feature.notes.domain.use_case.UpdateNote
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(application, LocalDatabase::class.java, "Local_database")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(localDatabase: LocalDatabase): NoteRepository =
        NoteRepositoryImpl(localDatabase.noteDao())

    @Provides
    @Singleton
    fun provideNoteUsecases(repository: NoteRepository): NoteUseCases =
        NoteUseCases(
            insertNote = InsertNote(repository),
            updateNote = UpdateNote(repository),
            deleteNote = DeleteNote(repository),
            getNoteById = GetNoteById(repository),
            getAllNotes = GetAllNotes(repository)
        )
}
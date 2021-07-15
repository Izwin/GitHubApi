package com.example.githubapp.di

import android.content.Context
import androidx.room.Room
import com.example.githubapp.repo.GitHubDao
import com.example.githubapp.repo.GitRoomDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GitRoomDataBase::class.java, "databaseGitHub").fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideDao(db: GitRoomDataBase) = db.gitHubDao()
}
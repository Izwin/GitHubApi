package com.example.githubapp.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapp.model.Converters
import com.example.githubapp.model.Item

@Database(entities = arrayOf(Item::class),version = 2,exportSchema = false)
@TypeConverters(Converters::class)
abstract class GitRoomDataBase : RoomDatabase() {
    abstract fun gitHubDao() : GitHubDao
}
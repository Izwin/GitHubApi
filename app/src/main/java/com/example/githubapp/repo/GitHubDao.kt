package com.example.githubapp.repo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubapp.model.Item

@Dao
interface GitHubDao {
    @Insert
    fun insertAll(vararg repo: Item)

    @Query("SELECT * FROM item")
    fun getDownloads() : List<Item>
}
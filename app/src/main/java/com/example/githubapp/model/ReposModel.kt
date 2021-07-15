package com.example.githubapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Owner(
    val login: String
)

@Entity
class Item(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    val name: String,
    val archive_url: String,
    val owner: Owner)

class ReposModel(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: ArrayList<Item>
)

package com.example.githubapp.repo

import androidx.room.Room
import com.example.githubapp.model.ReposModel
import com.example.githubapp.network.RetrofitClient
import java.util.concurrent.Flow

class GitHubRepo {
    private val gitHubService = RetrofitClient.create()
    private val accept = "application/json"

    suspend fun getRepos(name: String, page: Int): ReposModel {
        return  gitHubService.getRepos(accept, name, page)
    }
}
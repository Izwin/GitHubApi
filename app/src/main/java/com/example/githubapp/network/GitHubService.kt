package com.example.githubapp.network

import com.example.githubapp.model.ReposModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GitHubService {
    @GET("search/repositories")
    suspend fun getRepos(@Header("accept") accept: String,
                         @Query("q") repoName : String,
                         @Query("page") page: Int) : ReposModel
}
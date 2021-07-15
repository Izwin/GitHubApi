package com.example.githubapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.githubapp.model.Item
import com.example.githubapp.model.ReposModel
import com.example.githubapp.repo.GitHubDao
import com.example.githubapp.repo.GitHubRepo
import com.example.githubapp.repo.GitRoomDataBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val gitHubDao: GitHubDao) : ViewModel() {
    private val gitHubRepo = GitHubRepo()
    private val m_repos = MutableLiveData<ReposModel>()
    val repos = m_repos

    private val m_errors = MutableLiveData<String>()
    val errors = m_errors

    fun getRepos(name : String,  page: Int){
        if (name.isEmpty()){ errors.postValue("Please, fill field!");return}
        viewModelScope.launch {
            m_repos.postValue(gitHubRepo.getRepos(name, page))
        }
    }
    fun addRepoToDownloads(repo: Item){
        gitHubDao.insertAll(repo)
    }
}
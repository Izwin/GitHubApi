package com.example.githubapp.viewmodel

import android.content.Context
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
class DownloadsViewModel @Inject constructor(val gitHubDao: GitHubDao) : ViewModel() {
    private val m_repos = MutableLiveData<List<Item>>()
    val repos = m_repos

    private val m_errors = MutableLiveData<String>()
    val errors = m_errors

    fun getRepos(){
       m_repos.postValue(gitHubDao.getDownloads())
    }
}
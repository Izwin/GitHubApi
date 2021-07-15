package com.example.githubapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.Item
import com.example.githubapp.view.adapters.DownloadsAdapter
import com.example.githubapp.view.adapters.RepoAdapter
import com.example.githubapp.viewmodel.DownloadsViewModel
import com.example.githubapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repo_search.*
import javax.inject.Inject

@AndroidEntryPoint
class DownloadsFragment : Fragment(R.layout.fragment_downloads) {
    val viewmodel by viewModels<DownloadsViewModel>()
    @Inject lateinit var adapter:  DownloadsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rec_view.layoutManager = LinearLayoutManager(requireContext())
        rec_view.adapter = adapter

        viewmodel.repos.observe(requireActivity()){
            adapter.editList(it)
        }
        viewmodel.errors.observe(requireActivity()){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewmodel.getRepos()
    }
}
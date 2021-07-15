package com.example.githubapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.Item
import com.example.githubapp.view.adapters.RepoAdapter
import com.example.githubapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repo_search.*

@AndroidEntryPoint
class RepoSearchFragment : Fragment(R.layout.fragment_repo_search) {

    val viewmodel by viewModels<MainViewModel>()
    private var page = 1
    private lateinit var adapter: RepoAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /// Тут не использвал @Inject так как нужно было передать и clicklistener
        rec_view.layoutManager = LinearLayoutManager(requireContext())
        rec_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var loading = true
                if (dy > 0) { //check for scroll down
                    val visibleItemCount = recyclerView.layoutManager!!.getChildCount();
                    val totalItemCount = recyclerView.layoutManager!!.getItemCount();
                    val pastVisiblesItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            viewmodel.getRepos(editText.text.toString() , page++)
                            loading = true;
                        }
                    }
                }
            }
        })
        viewmodel.repos.observe(requireActivity()){
            adapter.addList(it)
        }
        viewmodel.errors.observe(requireActivity()){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
        search_button.setOnClickListener {
            adapter = RepoAdapter(requireContext()){p:Item ->
                viewmodel.addRepoToDownloads(p)
            }
            rec_view.adapter = adapter
            page = 1
            viewmodel.getRepos(editText.text.toString(), page)
        }

    }
}

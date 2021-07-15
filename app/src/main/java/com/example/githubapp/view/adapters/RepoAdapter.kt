package com.example.githubapp.view.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.Item
import com.example.githubapp.model.ReposModel
import kotlinx.android.synthetic.main.item_repo.view.*

class RepoAdapter(val context: Context ,val onDownloadClick: (Item) -> Unit) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {
    var reposModel: ReposModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = reposModel!!.items[position]
        var archive_link = convertLinkToArchive(repo.archive_url)
        var link = convertLinkToHTML(repo.archive_url)
        holder.login.text = repo.owner.login
        holder.name.text = repo.name
        holder.download.setOnClickListener { onDownloadClick.invoke(repo); context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(archive_link))) }
        holder.itemView.setOnClickListener { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))}
    }

    override fun getItemCount() = reposModel?.items?.size ?: 0

    fun addList(reposModel: ReposModel){
        if(this.reposModel == null)this.reposModel = reposModel
        else this.reposModel?.items?.addAll(reposModel.items)
        notifyDataSetChanged()
    }
    private fun convertLinkToArchive(url: String): String {
        //// Эта функция нужна, чтобы получить прямую ссылку на скачивание,
        //   так как api метод ее не возвращает
        var newUrl = url.replace("{archive_format}" , "archive")
        newUrl = newUrl.replace("{/ref}" , "").plus("/HEAD.zip")
        newUrl = newUrl.replace("api.", "")
        newUrl = newUrl.replace("/repos", "")
        return newUrl
    }
    private fun convertLinkToHTML(url: String): String {
        //// Эта функция нужна, чтобы получить прямую ссылку на репозиторий,
        //   так как api метод ее не возвращает
        var newUrl = url.replace("{archive_format}" , "")
        newUrl = newUrl.replace("{/ref}" , "").plus("")
        newUrl = newUrl.replace("api.", "")
        newUrl = newUrl.replace("/repos", "")
        return newUrl
    }
    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val login = itemView.login
        val name = itemView.name
        val download = itemView.download
    }
}
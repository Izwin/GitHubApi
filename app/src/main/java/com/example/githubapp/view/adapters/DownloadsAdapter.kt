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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.android.synthetic.main.item_repo.view.*
import javax.inject.Inject

class DownloadsAdapter @Inject constructor(@ApplicationContext val context: Context) : RecyclerView.Adapter<DownloadsAdapter.DownloadViewHolder>() {
    var itemList: List<Item>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_download, parent, false)
        return DownloadViewHolder(view)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        val repo = itemList!![position]
        var link = convertLinkToHTML(repo.archive_url)
        holder.login.text = repo.owner.login
        holder.name.text = repo.name
        holder.itemView.setOnClickListener { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))}
    }

    override fun getItemCount() = itemList?.size ?: 0

    fun editList(itemList: List<Item>){
        this.itemList = itemList
        notifyDataSetChanged()
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
    class DownloadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val login = itemView.login
        val name = itemView.name
    }
}
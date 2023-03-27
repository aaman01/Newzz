package com.example.newzz.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newzz.Article
import com.example.newzz.DetailActivity
import com.example.newzz.R

class NewsAdapter(
    val context: Context,
    val articles: List<Article>,



) :
    Adapter<NewsAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : ViewHolder(itemView) {
        var newsimage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newstitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsdescript = itemView.findViewById<TextView>(R.id.newsDescription)
        var newsauthor = itemView.findViewById<TextView>(R.id.newsauthor)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=articles[position]
            //image cannot be binded directly
        holder.newstitle.text=article.title
        holder.newsdescript.text= article.description
        holder.newsauthor.text=article.author
        Glide.with(context).load(article.urlToImage).into(holder.newsimage)
        holder.itemView.setOnClickListener {

           val intent= Intent(context,DetailActivity::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }




    }
}

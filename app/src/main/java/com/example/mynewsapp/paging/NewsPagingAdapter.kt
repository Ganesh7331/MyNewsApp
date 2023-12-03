package com.example.mynewsapp.paging

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.Activites.DetailsActivity
import com.example.mynewsapp.dataClass.Article
import com.example.mynewsapp.R
import com.example.mynewsapp.databinding.ViewHolderNewsBinding
import com.squareup.picasso.Picasso

class NewsPagingAdapter : PagingDataAdapter<Article, NewsPagingAdapter.ArticleViewHolder>(
    ARTICLE_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleItem = getItem(position)
        if (articleItem != null) {
            holder.bind(articleItem)
        }
    }

    class ArticleViewHolder(private val binding: ViewHolderNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.Itemtitle.text=article.title
            Picasso.get().load(article.urlToImage).into(binding.thumbnail)

            binding.root.setOnClickListener{
                val intent= Intent(it.context, DetailsActivity::class.java)
                intent.putExtra("name",article.source.name)
                intent.putExtra("title",article.title)
                intent.putExtra("author",article.author)
                intent.putExtra("time",article.publishedAt)
                intent.putExtra("image",article.urlToImage)
                intent.putExtra("content",article.content)
                intent.putExtra("site",article.url)
                ContextCompat.startActivity(it.context, intent, null)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ArticleViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_news, parent, false)
                val binding = ViewHolderNewsBinding.bind(view)
                return ArticleViewHolder(binding)
            }
        }
    }

    companion object {
        private val ARTICLE_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                // Replace with your own logic
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                // Replace with your own logic
                return oldItem == newItem
            }
        }
    }
}

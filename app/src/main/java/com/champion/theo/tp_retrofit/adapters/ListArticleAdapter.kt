package com.champion.theo.tp_retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.champion.theo.tp_retrofit.databinding.ArticleListItemBinding
import com.champion.theo.tp_retrofit.models.Article

class ListArticleAdapter(private val callback: Listener): RecyclerView.Adapter<ListArticleViewHolder>() {

    /**
     * View binding
     */
    private var _binding: ArticleListItemBinding? = null
    private val binding: ArticleListItemBinding = _binding!!

    /**
     * Live data articles
     */
    private var articles: List<Article> = ArrayList<Article>()

    interface Listener {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListArticleViewHolder {
        _binding = ArticleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ListArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListArticleViewHolder, position: Int) {
        holder.bind(articles[position], callback)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    /**
     * Setter for articles
     */
    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }
}

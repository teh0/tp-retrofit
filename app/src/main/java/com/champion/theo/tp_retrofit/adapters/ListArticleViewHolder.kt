package com.champion.theo.tp_retrofit.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.champion.theo.tp_retrofit.databinding.ArticleListItemBinding
import com.champion.theo.tp_retrofit.models.Article

class ListArticleViewHolder(var binding: ArticleListItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article, callback: ListArticleAdapter.Listener) {
        // Insert image into ViewImage
        Glide.with(binding.root.context)
            .load(article.urlToImage)
            .into(binding.articleListThumbnail)
    }
}
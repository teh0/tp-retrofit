package com.champion.theo.tp_retrofit.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.tp_retrofit.models.Article
import com.champion.theo.tp_retrofit.repositories.ArticleRepository
import java.util.concurrent.Executors

class ListArticleViewModel: ViewModel() {

    /**
     * Repository
     */
    private var repository: ArticleRepository = ArticleRepository.getInstance()

    /**
     * Articles
     */
    private var _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> get() = _articles

    init {
        loadArticles()
    }

    private fun loadArticles() {
        Executors.newSingleThreadExecutor().execute {
            _articles.postValue(repository.getArticles("covid").value)
        }
    }
}
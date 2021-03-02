package com.champion.theo.tp_retrofit.repositories

import androidx.lifecycle.LiveData
import com.champion.theo.tp_retrofit.dal.NewsDataSource
import com.champion.theo.tp_retrofit.dal.online.ArticleOnlineDataSource
import com.champion.theo.tp_retrofit.models.Article

class ArticleRepository private constructor() {
    private val dataSource: NewsDataSource = ArticleOnlineDataSource()

    fun getArticles(query: String): LiveData<List<Article>> {
        return dataSource.getArticles(query)
    }

    companion object {
        private var instance: ArticleRepository? = null

        fun getInstance(): ArticleRepository {

            if (instance == null) {
                instance = ArticleRepository()
            }

            return instance!!
        }
    }
}
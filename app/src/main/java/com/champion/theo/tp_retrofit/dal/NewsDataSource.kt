package com.champion.theo.tp_retrofit.dal

import androidx.lifecycle.LiveData
import com.champion.theo.tp_retrofit.models.Article

interface NewsDataSource {
    fun getArticles(query: String): LiveData<List<Article>>
}
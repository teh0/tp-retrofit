package com.champion.theo.tp_retrofit.dal.utils

import com.champion.theo.tp_retrofit.dal.online.models.ItemResponse
import com.champion.theo.tp_retrofit.dal.online.models.SourceResponse
import com.champion.theo.tp_retrofit.models.Article
import com.champion.theo.tp_retrofit.models.Source


fun ItemResponse.toArticle() = Article(
    author = author?:null,
    title = title,
    description = description?:null,
    publishedAt = publishedAt,
    source = source.toSource()?:null,
    url = url,
    urlToImage = urlToImage,
    content = content?:null
)

fun SourceResponse.toSource() = Source(
    id = id?:"",
    name = name
)
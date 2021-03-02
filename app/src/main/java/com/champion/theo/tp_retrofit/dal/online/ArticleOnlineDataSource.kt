package com.champion.theo.tp_retrofit.dal.online

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.champion.theo.tp_retrofit.dal.NewsDataSource
import com.champion.theo.tp_retrofit.dal.utils.toArticle
import com.champion.theo.tp_retrofit.models.Article
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleOnlineDataSource: NewsDataSource {
    private val service: RetrofitApiService

    init {
        val retrofit = buildClient()
        //Init the api service with retrofit
        service = retrofit.create(RetrofitApiService::class.java)
    }

    /**
     * Configure retrofit
     */
    private fun buildClient(): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addLogInterceptor(this)
            addApiInterceptor(this)
        }.build()

        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    /**
     * Add a logger to the client so that we log every request
     */
    private fun addLogInterceptor(builder: OkHttpClient.Builder) {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        builder.addNetworkInterceptor(httpLoggingInterceptor)
    }

    /**
     * Intercept every request to the API and automatically add
     * the api key as query parameter
     */
    private fun addApiInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(Interceptor { chain ->

                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", apiKey)
                    .build()

                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
        })
    }

    override fun getArticles(query: String): LiveData<List<Article>> {
        val _data = MutableLiveData<List<Article>> ()

        val articleList = service.list(query).execute().body()?.articles ?: listOf()

        val articles = articleList.map {
            it.toArticle()
        }

        _data.postValue(articles)

        return _data
    }

    companion object {
        private const val apiKey = "ce187aeee7fb4d5baf1990fa07041505"
        private const val apiUrl = "https://newsapi.org/v2/"
    }
}


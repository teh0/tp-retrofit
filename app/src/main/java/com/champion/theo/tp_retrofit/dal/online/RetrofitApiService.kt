package com.champion.theo.tp_retrofit.dal.online

import com.champion.theo.tp_retrofit.dal.online.models.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    @GET("everything")
    fun list(@Query("q") query: String): Call<ArticleResponse>
}
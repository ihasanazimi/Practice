package ir.ha.myapplication.services.http

import ir.ha.myapplication.model.Article
import retrofit2.http.POST

interface MyApiService {

    @POST("article/all")
    suspend fun getAllArticle() : List<Article>


}
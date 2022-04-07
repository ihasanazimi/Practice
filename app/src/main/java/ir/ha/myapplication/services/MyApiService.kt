package ir.ha.myapplication.services

import ir.ha.myapplication.model.ArticleModel
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApiService {

    @POST("article/all")
    suspend fun getAllArticle() : List<ArticleModel>


}
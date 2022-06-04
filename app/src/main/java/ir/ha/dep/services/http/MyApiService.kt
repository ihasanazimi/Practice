package ir.ha.dep.services.http

import ir.ha.dep.model.Article
import retrofit2.http.POST

interface MyApiService {

    @POST("article/all")
    suspend fun getAllArticle() : List<Article>


}
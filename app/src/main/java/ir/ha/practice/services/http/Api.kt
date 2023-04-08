package ir.ha.practice.services.http

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.DeveloperDetails
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("5f9e6189-805e-4e44-9b52-bcf507259c06")
    suspend fun getDevelopersByCoroutines() : DeveloperDetails

    @GET("5f9e6189-805e-4e44-9b52-bcf507259c06")
    fun getDevelopersByRx() : Observable<DeveloperDetails>

    @GET("5f9e6189-805e-4e44-9b52-bcf507259c06")
    fun getDevelopersBySingleObservable() : Single<DeveloperDetails>

    @GET("5f9e6189-805e-4e44-9b52-bcf507259c06")
    fun getDevelopersByRetrofit() : Call<DeveloperDetails>


}
package ir.ha.practice.services.http

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.Developers
import ir.ha.practice.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("b9e6a9d9-89cd-4053-ad75-ab0ca70d44c2")
    suspend fun getDevelopersByCoroutines() : Developers

    @GET("b9e6a9d9-89cd-4053-ad75-ab0ca70d44c2")
    fun getDevelopersByRx() : Observable<Developers>

    @GET("b9e6a9d9-89cd-4053-ad75-ab0ca70d44c2")
    fun getDevelopersBySingleObservable() : Single<Developers>

    @GET("b9e6a9d9-89cd-4053-ad75-ab0ca70d44c2")
    fun getDevelopersByRetrofit() : Call<Developers>


}
package ir.ha.practice.services.http

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.DeveloperDetails
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    suspend fun getDevelopersByCoroutines() : DeveloperDetails

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersByRx() : Observable<DeveloperDetails>

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersBySingleObservable() : Single<DeveloperDetails>

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersByRetrofit() : Call<DeveloperDetails>


}
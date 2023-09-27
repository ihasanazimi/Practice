package ir.ha.practice.services.http

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.model.data.DeveloperDetailsRemoteResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    suspend fun getDevelopersByCoroutines() : DeveloperDetailsRemoteResponse

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersByRx() : Observable<DeveloperDetailsRemoteResponse>

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersBySingleObservable() : Single<DeveloperDetailsRemoteResponse>

    @GET("8bf97e92-a3f7-4e4c-bb0f-75ac9412eca6")
    fun getDevelopersByRetrofit() : Call<DeveloperDetailsRemoteResponse>


}
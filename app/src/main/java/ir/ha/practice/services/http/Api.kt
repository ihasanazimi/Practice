package ir.ha.practice.services.http

import io.reactivex.Observable
import io.reactivex.Single
import ir.ha.practice.data.remote_data.DeveloperDetailsRemoteResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("06945cde-487a-4dd1-8f22-059002444ded")
    suspend fun getDevelopersByCoroutines() : DeveloperDetailsRemoteResponse

    @GET("06945cde-487a-4dd1-8f22-059002444ded")
    fun getDevelopersByRx() : Observable<DeveloperDetailsRemoteResponse>

    @GET("06945cde-487a-4dd1-8f22-059002444ded")
    fun getDevelopersBySingleObservable() : Single<DeveloperDetailsRemoteResponse>

    @GET("06945cde-487a-4dd1-8f22-059002444ded")
    fun getDevelopersByRetrofit() : Call<DeveloperDetailsRemoteResponse>


}
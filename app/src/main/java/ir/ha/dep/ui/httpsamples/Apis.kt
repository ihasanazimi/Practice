package ir.ha.dep.ui.httpsamples

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface Apis {

    @GET("/")
    fun testRequest() : Call<String>

    @POST("")
    fun tst(@Body tst : String) : Call<String>

    @DELETE("")
    fun delete(tst :String) : Call<String>

    @PUT("")
    fun update(tst :String) : Call<String>
}
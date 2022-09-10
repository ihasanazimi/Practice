package ir.ha.dummy.ui.fragment.httpSamples

import io.reactivex.Single
import ir.ha.dummy.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface Apis {

    @GET("/posts")
    fun getPosts() : Single<List<UserModel>>

    @GET("/posts")
    fun getPostsByRetrofit() : Call<List<UserModel>>

    @POST("")
    fun tst(@Body tst : String) : Call<String>

    @DELETE("")
    fun delete(tst :String) : Call<String>

    @PUT("")
    fun update(tst :String) : Call<String>
}
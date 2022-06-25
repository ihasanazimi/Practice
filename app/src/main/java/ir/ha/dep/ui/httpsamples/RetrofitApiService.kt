package ir.ha.dep.ui.httpsamples

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiService() {

    // app interceptor (set token on all request)
    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(Interceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
//            newRequestBuilder.addHeader(App().context!!.getString(R.string.tokenKey), SingletonPrefManager.inctance!!.getToken())
            return@Interceptor it.proceed(newRequestBuilder.build())
        }).build()

    private val baseUrl = "http://mobofit.ir/"

    val apis: Apis by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()

        retrofit.create(Apis::class.java)
    }
}
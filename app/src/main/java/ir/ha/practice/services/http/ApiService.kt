package ir.ha.practice.services.http

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiService {

    private val baseUrl = "https://mocki.io/v1/"

    private fun logger(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
//        if (BuildConfig.DEBUG) interceptor.level = HttpLoggingInterceptor.Level.BODY
//        else interceptor.level = HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    private val tokenClient = OkHttpClient.Builder()
        .addInterceptor(logger())
        .addInterceptor(Interceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
            newRequestBuilder.addHeader("x-token" , "")
            return@Interceptor it.proceed(newRequestBuilder.build())
        }).build()


    val api : Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(tokenClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }

}
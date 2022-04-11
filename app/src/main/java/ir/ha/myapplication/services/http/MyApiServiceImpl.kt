package ir.ha.myapplication.services.http

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

    val apiService : MyApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mobofit.ir/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MyApiService::class.java)
}
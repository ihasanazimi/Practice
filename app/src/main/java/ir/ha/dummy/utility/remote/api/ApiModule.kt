package ir.ha.dummy.utility.remote.api


import androidx.databinding.ktx.BuildConfig
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    private val retrofitService : ApiService by lazy {

//        //test
//        var mainUrl = KtPrefs.mainApiUrl
//        if (mainUrl!!.contains("https") || mainUrl.contains("http")){
//            mainUrl = "$mainUrl/edge/v1/"
//        }else{
//            mainUrl = "https://"+KtPrefs.mainApiUrl+"/edge/v1/"
//        }


        Retrofit.Builder()
            .baseUrl("")
            .client(makeOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(JacksonConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
            .build().create(ApiService::class.java)
    }
    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService {
        return retrofitService
    }



    private fun makeOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG) {
                addInterceptor(makeLoggingInterceptor())
            }
            val dispathcer = Dispatcher()
            dispathcer.maxRequests = 16
            dispathcer.maxRequestsPerHost = 16
            dispatcher(dispathcer)
            addInterceptor(RetrofitInterceptor())
            connectTimeout(5, TimeUnit.MINUTES)
            connectionPool(ConnectionPool(20,5, TimeUnit.MINUTES))
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
        }
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level =
            HttpLoggingInterceptor.Level.BODY
        return logging
    }
}

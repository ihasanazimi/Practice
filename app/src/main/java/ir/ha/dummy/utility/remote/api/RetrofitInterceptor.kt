package ir.ha.dummy.utility.remote.api

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException


class RetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {

        try {
            return@run proceed(
                request()
                    .newBuilder()
//                    .doIf(KtPrefs.userJWT!!.isNotEmpty()) {
//                        addHeader("Authorization", KtPrefs.userJWT!!) }
                        .addHeader("Content-Type", "application/json")
                    .build()
            ).also {
                if (it.code == 403 || it.code == 401) {
//                    KtPrefs.userJWT = ""
//                    KtPrefs.userLogin = false
                }
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            return Response.Builder()
                .code(e.code()) //Or whatever you might later check from
                .protocol(Protocol.HTTP_2) //or 1
                .message(e.message()) // your json
                .body(
                    """
                    {
                        "error": "${e.message()}"
                    }
                """.trimIndent().toResponseBody("application/json".toMediaType())
                )
                .request(request())
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            return Response.Builder()
                .code(600)
                .protocol(Protocol.HTTP_2) //or 1
                .message(e.message ?: "Unknown Error") // your json
                .body(
                    """
                    {
                        "error": "${e.message ?: "Unknown Error"}"
                    }
                """.trimIndent().toResponseBody("application/json".toMediaType())
                )
                .request(request())
                .build()
        }
    }
}
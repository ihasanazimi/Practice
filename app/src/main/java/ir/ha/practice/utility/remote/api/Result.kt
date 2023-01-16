package ir.ha.practice.utility.remote.api

import ir.ha.practice.utility.extentions.fromJson
import retrofit2.HttpException
import retrofit2.Response

enum class ResultStatus{
    Success,
    Loading,
    Error
}

data class Result<T>(
    var value: T?,
    var error: Any?,
    var status: ResultStatus = ResultStatus.Loading
) {

    var networkError: NetworkError? = null

    companion object {
        fun <T> success(value: T?): Result<T> {
            val ans = Result(value, null, ResultStatus.Success)
            ans.code = 200
            return ans
        }
        fun <T> success(value: Response<T>?): Result<T> {
            val ans = Result(value?.body(), null, ResultStatus.Success)
            ans.code = 200
            return ans
        }
        fun <T> loading(value: T? = null) = Result(value, null, ResultStatus.Loading)
        fun <T> error(value: T? = null, error: Any?) = Result(value, error, ResultStatus.Error)

        fun <T> error(value: T? = null, error: HttpException): Result<T> {
            val ans = Result(value, error, ResultStatus.Error)
            ans.code = error.code()
            try {
                ans.networkError = error.response()?.errorBody()?.string()?.fromJson()
            }catch (e: Exception){
                e.printStackTrace()
            }
            return ans
        }

        fun <T> error(value: Response<T>? = null): Result<T> {
            val res = Result(value?.body(), null, ResultStatus.Error)
            try {
                val holder = value?.errorBody()?.string().toString()
                res.networkError = holder?.fromJson()
                res.error =  res.networkError
            }catch (e: Exception){
                e.printStackTrace()
            }
            return res
        }

        fun <T> copyError(other: Result<*>) = Result<T>(null, other.error, other.status).apply {
            networkError = other.networkError
        }
    }

    var code : Int = 0
    fun code() = code

    inline val isSuccessful get() = status == ResultStatus.Success
    inline val isLoading get() = status == ResultStatus.Loading
    inline val isError get() = status == ResultStatus.Error


}
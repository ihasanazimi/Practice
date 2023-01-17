//package ir.ha.practice.utility.remote.api
//
//import kotlinx.coroutines.delay
//import retrofit2.HttpException
//import retrofit2.Response
//
//@Suppress("UNCHECKED_CAST")
//suspend fun <T> apiCall(tryTimes:Int = 2,delay:Long = 2000 , apiFun: suspend ApiService.() -> Response<T>): Result<T> {
//    return try {
//        val res = apiFun(ApiModule.provideRetrofitService())
//        if (res.isSuccessful)
//            Result.success(res)
//        else{
//            if(tryTimes>0){
//                delay(delay)
//                return apiCall(tryTimes-1,delay,apiFun)
//            }else
//                Result.error(res)
//        }
//    } catch (e: HttpException) {
//        if(tryTimes>0){
//            delay(delay)
//            return apiCall(tryTimes-1,delay,apiFun)
//        }else
//            Result.error(null, e)
//    } catch (e: Exception) {
//        e.printStackTrace()
//        if(tryTimes>0){
//            delay(delay)
//            return apiCall(tryTimes-1,delay,apiFun)
//        }else
//            Result.error(null, e)
//    }
//}
//

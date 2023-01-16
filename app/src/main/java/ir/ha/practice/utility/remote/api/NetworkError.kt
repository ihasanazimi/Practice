package ir.ha.practice.utility.remote.api

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName as SN
@JsonIgnoreProperties(ignoreUnknown = true)
data class NetworkError (

    @SN("code")
    val code: Int? = null,

    @SN("msg", alternate = ["errorMessage"])
    val message: String? = null,

    @SN("status")
    val status:String? = null
){

    override fun toString(): String {
        return message?:super.toString()
    }
}
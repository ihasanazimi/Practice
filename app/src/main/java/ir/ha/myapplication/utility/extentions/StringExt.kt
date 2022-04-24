package ir.ha.myapplication.utility

import com.google.gson.Gson


fun convertNumbersToFarsiNum(faNumbers: String): String {
    var changedVariable = ""
    val mChars = arrayOf(
        arrayOf("0", "۰"),
        arrayOf("1", "۱"),
        arrayOf("2", "۲"),
        arrayOf("3", "۳"),
        arrayOf("4", "۴"),
        arrayOf("5", "۵"),
        arrayOf("6", "۶"),
        arrayOf("7", "۷"),
        arrayOf("8", "۸"),
        arrayOf("9", "۹")
    )
    for (num in mChars) {
        changedVariable = faNumbers.replace(num[0], num[1])
    }
    return faNumbers
}

inline fun <reified T:Any> String.fromJson(): T? {
    return Gson().fromJson(this, T::class.java)
}
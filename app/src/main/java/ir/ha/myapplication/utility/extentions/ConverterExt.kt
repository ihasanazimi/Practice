package ir.ha.myapplication.utility.extentions

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import com.google.gson.Gson
import kotlin.math.ceil

fun convertDpToPixel(dp : Float , context : Context?) : Float {
    return if (context != null){
        val resource = context.resources
        val metrics = resource.displayMetrics
        dp / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }else{
        val metrics = Resources.getSystem().displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

fun View.dp(value: Float): Int {
    return if (value == 0f) {
        0
    } else ceil(context.resources.displayMetrics.density * value.toDouble()).toInt()
}

fun convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
}




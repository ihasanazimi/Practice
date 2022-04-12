package ir.ha.myapplication.util

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce


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


/**
 * first imp this dependency on build.gradle :
 * implementation 'androidx.dynamicanimation:dynamicanimation:1.0.0'
 */
fun View.implementSpringAnimationTrait(){
    val scaleXAnim = SpringAnimation(this,DynamicAnimation.SCALE_X,0.9f)
    val scaleYanim = SpringAnimation(this,DynamicAnimation.SCALE_Y,0.9f)

    setOnTouchListener { view, event ->

        Log.i("setOnTouchListener", "implementSpringAnimationTrait: " + event.action.toString()) // log for test (print event actions)

        when(event.action){
            MotionEvent.ACTION_DOWN ->{
                scaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                scaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                scaleXAnim.start()
                scaleYanim.spring.dampingRatio = SpringForce.STIFFNESS_LOW
                scaleYanim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
            }

            MotionEvent.ACTION_UP , MotionEvent.ACTION_CANCEL ->{
                scaleXAnim.cancel()
                scaleYanim.cancel()
                val reverseScaleXAnim = SpringAnimation(this,DynamicAnimation.SCALE_Y,0.9f)
                reverseScaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                val reverseScaleYAnim = SpringAnimation(this,DynamicAnimation.SCALE_Y,0.9f)
                reverseScaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                reverseScaleYAnim.start()
            }
        }
        false
    }
}


fun checkConnection(context: Context?):Boolean{
    var isConnected = false
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        isConnected = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            activeNetworkInfo?.run {
                isConnected = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return isConnected
}




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



fun checkInternetConnection(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}


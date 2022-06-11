package ir.ha.dep.utility.extentions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import ir.ha.dep.R
import ir.ha.dep.ui.App
import java.util.*


fun AppCompatActivity.addFragmentByAnimation(
    fragment: Fragment,
    tag: String,
    addToBackStack: Boolean,
    customAnimations: Boolean,
    containerViewId: Int,
    commitAllowingStateLoss: Boolean = false
) {

    val fragmentTransaction = supportFragmentManager.beginTransaction()

    if (customAnimations) {
        fragmentTransaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
    }

    if (addToBackStack) { fragmentTransaction.addToBackStack(tag) }
    fragmentTransaction.add(containerViewId, fragment, tag)
    if (commitAllowingStateLoss) { fragmentTransaction.commitAllowingStateLoss()
    } else { fragmentTransaction.commit() }
}



fun Fragment.getDrawable(drawableResID: Int): Drawable? =
    ContextCompat.getDrawable(requireContext(), drawableResID)?.mutate()

fun Fragment.getColor(colorResID: Int): Int =
    ContextCompat.getColor(requireContext(), colorResID)




fun AppCompatActivity.getDrawable(drawableResID: Int): Drawable? =
    ContextCompat.getDrawable(this, drawableResID)?.mutate()

fun AppCompatActivity.getColor(colorResID: Int): Int =
    ContextCompat.getColor(this, colorResID)




fun PopupMenu.insertMenuItemIcons(
    context: Context,
) {
    if (hasIcon()) {
        for (i in 0 until menu.size()) {
            insertMenuItemIcon(context, menu.getItem(i))
        }
    }
}


fun PopupMenu.hasIcon(): Boolean {
    for (i in 0 until menu.size()) {
        if (menu.getItem(i).icon != null) return true
    }
    return false
}



fun PopupMenu.insertMenuItemIcon(context: Context, menuItem: MenuItem) {
    var icon: Drawable? = menuItem.icon
    if (icon == null) icon = ColorDrawable(Color.TRANSPARENT)
    val iconSize: Int = context.resources.getDimensionPixelSize(R.dimen.size_8dp)
    icon.setBounds(0, 0, iconSize, iconSize)
    val imageSpan = ImageSpan(icon)
    val ssb = SpannableStringBuilder("     " + menuItem.title)
    ssb.setSpan(imageSpan, 1, 2, 0)
    menuItem.title = ssb
    menuItem.icon = null
}



fun Context.drawable(@DrawableRes drawableRes: Int) = ResourcesCompat.getDrawable(resources, drawableRes, theme)


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



fun convertDpToPixel2(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
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




inline fun <reified T:Any> String.fromJson(): T? {
    return Gson().fromJson(this, T::class.java)
}



fun isNonNull(o: Any?): Boolean {
    return o != null
}

fun isNull(o: Any?): Boolean {
    return o == null
}

fun isNotZero(f: Float): Boolean {
    return f != 0f
}



@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
fun isNougatMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
fun isOreoMr1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
fun isQPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun isSPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S


fun showToast(message : String){
    Toast.makeText(App().applicationContext,message.trim() ,Toast.LENGTH_LONG).show()
}


fun setStatusBarTransparent(activity: Activity, view: View) {
    activity.apply {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(view) { root, windowInset ->
            val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                leftMargin = inset.left
                bottomMargin = inset.bottom
                rightMargin = inset.right
            }
            WindowInsetsCompat.CONSUMED
        }
    }
}



fun convertMilliSecondToMinute(millisecond: Long): String? {
    val second = (millisecond / 1000) % 60
    val minute = (millisecond / (1000 * 60)) % 60
    return String.format(Locale.US,"%02d:%02d",minute,second)
}


fun checkConnection(context: Context?): Boolean {
    var isConnected = false
    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (isMarshmallowPlus()) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
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



fun checkInternetConnection(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}





//fun CustomTextView.setIconWithText(icon: Drawable?){
//    val internalIcon = icon ?: ColorDrawable(Color.TRANSPARENT)
//    val iconSize: Int =
//        context.resources.getDimensionPixelSize(R.dimen.menu_item_icon_size)
//    internalIcon.setBounds(0, 0, iconSize, iconSize)
//    val imageSpan = ImageSpan(internalIcon)
//    val ssb = SpannableStringBuilder("     " + context.getString(R.string.AllActivities))
//    ssb.setSpan(imageSpan, 1, 2, 0)
//    text = ssb
//}



//
//fun Fragment.runOnUIThread(runnable: Runnable, delay: Long = 0) {
//    if (delay == 0L) {
//        ApplicationLoader.applicationHandler.post(runnable)
//    } else {
//        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
//    }
//}




//fun AppCompatActivity.runOnUIThread(runnable: Runnable, delay: Long = 0) {
//    if (delay == 0L) {
//        ApplicationLoader.applicationHandler.post(runnable)
//    } else {
//        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
//    }
//}




//fun View.runOnUIThread(runnable: Runnable, delay: Long = 0) {
//    if (delay == 0L) {
//        ApplicationLoader.applicationHandler.post(runnable)
//    } else {
//        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
//    }
//}
package ir.ha.practice.utility.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.ClipboardManager
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ImageSpan
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.google.gson.Gson
import ir.ha.practice.ApplicationLoader
import ir.ha.practice.R
import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*
import java.util.regex.Pattern


fun Fragment.onBackClick(callback: (onBackPressedCallback: OnBackPressedCallback) -> Unit) {
// This callback will only be called when MyFragment is at least Started.
    activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
        callback.invoke(this)
    }
}

fun Fragment.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        ApplicationLoader.applicationHandler.post(runnable)
    } else {
        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
    }
}



fun AppCompatActivity.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        ApplicationLoader.applicationHandler.post(runnable)
    } else {
        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
    }
}



fun View.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        ApplicationLoader.applicationHandler.post(runnable)
    } else {
        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
    }
}


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
    val iconSize: Int = context.resources.getDimensionPixelSize(R.dimen.space_8)
    icon.setBounds(0, 0, iconSize, iconSize)
    val imageSpan = ImageSpan(icon)
    val ssb = SpannableStringBuilder("     " + menuItem.title)
    ssb.setSpan(imageSpan, 1, 2, 0)
    menuItem.title = ssb
    menuItem.icon = null
}




fun Context.drawable(@DrawableRes drawableRes: Int) = ResourcesCompat.getDrawable(resources, drawableRes, theme)




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



fun String.extractNumberInString() = replace("\\D+".toRegex(),"").toInt()


fun Context.copyToClipboard(text: String){
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = text
    } else {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(getString(R.string.string_msg_clipboard_message), text))
    }
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

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
fun isSV2() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
fun isTIRAMISU() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU




 @SuppressLint("ServiceCast")
 fun isMyServiceRunning(applicationContext: Context?, serviceClass: Class<*>): Boolean {
    val manager = applicationContext?.getSystemService(Context.ACCOUNT_SERVICE) as ActivityManager?
    for (service in manager!!.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
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




fun isAppAvailable(context: Context, appName: String): Boolean {
    val pm = context.packageManager
    return try {
        pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}



fun convertMilliSecondToMinute(millisecond: Long): String? {
    val second = (millisecond / 1000) % 60
    val minute = (millisecond / (1000 * 60)) % 60
    return String.format(Locale.US,"%02d:%02d",minute,second)
}


fun getApplicationVersion(context : Context) : Pair<String , Int>{
    var versionName = ""
    var versionCode = -1
    try {
        val pInfo: PackageInfo = context.packageManager.getPackageInfo(context.getPackageName(), 0)
        versionName = pInfo.versionName
        versionCode = pInfo.versionCode
    } catch (e: PackageManager.NameNotFoundException) { e.printStackTrace() }
    return Pair(versionName , versionCode)
}


private fun turnOnGPS (activity: Activity) {
    val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER).not()) {
        // turnOnGPS
        val intent = Intent("android.location.GPS_ENABLED_CHANGE")
        intent.putExtra("enabled", true)
        activity.sendBroadcast(intent)
    }
}

fun changeStatusBarColor(window: Window, colorId : Int) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = ApplicationLoader.context?.resources!!.getColor(colorId,null)
    }
}



fun checkPermission(activity: Activity , permission: String, requestCode: Int) {
    if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
        // Requesting the permission
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    } else {
        Toast.makeText(activity, "Permission already granted", Toast.LENGTH_SHORT).show()
    }
}

fun Activity.statusBarIconsColor(shouldBeLight :Boolean){
    val insetsControllerCompat = WindowInsetsControllerCompat(window,window.decorView)
    insetsControllerCompat.isAppearanceLightStatusBars = !shouldBeLight
}

fun Activity.setStatusBarColor(color: Int) {
    val window = this.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}

fun NavController.navigateSafe(direction: NavDirections, navOptions: NavOptions? = null) {

    currentDestination?.getAction(direction.actionId)?.let {
        navigate(direction.actionId, direction.arguments, navOptions)
    }
}

private val viewScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
fun View.singleClick(callback: () -> Unit) {
    this.setOnClickListener {
        viewScope.launch {
            callback.invoke()
            this@singleClick.isClickable = false
            delay(300)
            this@singleClick.isClickable = true
            this.cancel()
        }
    }
}

fun keepOnlyNumbers(text: String): String {
    val regex = "[0-9]|[۰-۹]|[٠١٢٣٤٥٦٧٨٩]"
    var result = ""
    val pattern = Pattern.compile(regex, Pattern.MULTILINE)
    val matcher = pattern.matcher(text)
    while (matcher.find()) {
        result += matcher.group(0)
    }
    return result
}

fun String.removeNumbers(): String {
    return try {
        this.replace("0", "")
            .replace("1", "")
            .replace("2", "")
            .replace("3", "")
            .replace("4", "")
            .replace("5", "")
            .replace("6", "")
            .replace("7", "")
            .replace("8", "")
            .replace("9", "")
            .replace("۰", "")
            .replace("۱", "")
            .replace("۲", "")
            .replace("۳", "")
            .replace("۴", "")
            .replace("۵", "")
            .replace("۶", "")
            .replace("۷", "")
            .replace("۸", "")
            .replace("۹", "")

    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

fun getStandardMobileNumber(mobileNumber: String): String {
    var result = mobileNumber
    return try {
        result = result.replace(" ", "")
        result = result.replace("+98", "")
        if (result.length == 11) result = result.substring(1)
        result
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        mobileNumber
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}

fun EditText.afterTextChangedEditable(afterTextChanged: (Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable)
        }
    })
}








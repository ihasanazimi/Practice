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
import android.animation.*
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import android.text.InputType
import android.text.Selection
import android.text.Spannable
import android.text.SpannableString
import android.transition.Fade
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.*
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import ir.ha.practice.App
import ir.ha.practice.R
import kotlinx.coroutines.*
import java.lang.Runnable
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.math.abs
import kotlin.reflect.KClass


private const val TAG = "utility_extensions"

fun isNonNull(o: Any?) = o != null
fun isNull(o: Any?) = o == null
fun isNotZero(f: Float) = f != 0f

inline fun <reified T:Any> String.fromJson(): T? {
    return Gson().fromJson(this, T::class.java)
}

/** Activity AND Fragment Extensions */
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
    if (addToBackStack) fragmentTransaction.addToBackStack(tag)
    fragmentTransaction.add(containerViewId, fragment, tag)
    if (commitAllowingStateLoss) fragmentTransaction.commitAllowingStateLoss()
    else fragmentTransaction.commit()
}

fun Fragment.addFragmentByAnimation(
    fragment: Fragment,
    tag: String,
    addToBackStack: Boolean,
    customAnimations: Boolean,
    containerViewId: Int,
    commitAllowingStateLoss: Boolean = false
) {

    val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
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
    if (commitAllowingStateLoss)  fragmentTransaction.commitAllowingStateLoss()
    else fragmentTransaction.commit()
}


fun Activity.setStatusBarLightMode(shouldBeLight :Boolean){
    val insetsControllerCompat = WindowInsetsControllerCompat(window,window.decorView)
    insetsControllerCompat.isAppearanceLightStatusBars = !shouldBeLight
}

fun Activity.setStatusBarColor(color: Int) {
    val window = this.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}



fun Fragment.getDrawable(drawableResID: Int): Drawable? = ContextCompat.getDrawable(requireContext(), drawableResID)?.mutate()

fun Fragment.getColor(colorResID: Int): Int = ContextCompat.getColor(requireContext(), colorResID)

fun AppCompatActivity.getDrawable(drawableResID: Int): Drawable? = ContextCompat.getDrawable(this, drawableResID)?.mutate()

fun AppCompatActivity.getColor(colorResID: Int): Int = ContextCompat.getColor(this, colorResID)


fun Fragment.onBackClick(callback: (onBackPressedCallback: OnBackPressedCallback) -> Unit) {
// This callback will only be called when MyFragment is at least Started.
    activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
        callback.invoke(this)
    }
}


fun Fragment.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        App.applicationHandler.post(runnable)
    } else {
        App.applicationHandler.postDelayed(runnable, delay)
    }
}


fun AppCompatActivity.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        App.applicationHandler.post(runnable)
    } else {
        App.applicationHandler.postDelayed(runnable, delay)
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

fun EditText.customRequestFocus() {
    requestFocus()
    val imm: InputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !value
    isFocusableInTouchMode = !value
    this.inputType = inputType
}
fun RecyclerView.scrollToTop() {
    if(canScrollVertically(-1)) smoothScrollToPosition(0)
}
fun ViewGroup.showParentByAnim() {
    val transition = Fade()
    transition.duration = 500
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(this, transition)
    this.visibility = View.VISIBLE
}

/** get resources */
fun View.getDrawable(drawableResID: Int): Drawable? =
    ContextCompat.getDrawable(context, drawableResID)?.mutate()

fun View.getColor(colorResID: Int): Int =
    ContextCompat.getColor(context, colorResID)

fun View.getColoredDrawable(drawableResID: Int, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val drawable = ContextCompat.getDrawable(context, drawableResID)?.mutate()
    val colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, colorResID), mode)
    drawable?.colorFilter = colorFilter
    return drawable
}



fun View.shakeView(x: Float, num: Int, finish: Boolean = false) {

    if (finish) translationX = 0f

    val animatorSet = AnimatorSet()
    val valueAnimator = ObjectAnimator.ofFloat(this, "translationX", dp(x).toFloat())
    animatorSet.playTogether(valueAnimator)
    valueAnimator.repeatCount = ValueAnimator.INFINITE
    animatorSet.duration = 50
    animatorSet.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            if (!finish) shakeView(if (num == 5) 0f else -x, num + 1)
        }
    })
    animatorSet.start()
}

fun View.getColoredDrawable(drawable: Drawable, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, colorResID), mode)
    drawable.colorFilter = colorFilter
    return drawable
}

fun View.beHideIf(beInvisible: Boolean) = if (beInvisible) hide() else show()

fun View.beShowIf(beVisible: Boolean) = if (beVisible) show() else hide()

fun View.beGoneIf(beGone: Boolean) = beShowIf(!beGone)

fun View.setPaddingLeft(value: Int) {
    setPadding(value, paddingTop, paddingRight, paddingBottom)
}
fun View.setPaddingTop(value: Int) {
    setPadding(paddingLeft, value, paddingRight, paddingBottom)
}
fun View.setPaddingRight(value: Int) {
    setPadding(paddingLeft, paddingTop, value, paddingBottom)
}
fun View.setPaddingBottom(value: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, value)
}
fun View.showByFadeIn() {
    animate().alpha(1f).setDuration(150L).withStartAction { show() }.start()
}

fun View.hideFadeOut() {
    animate().alpha(0f).setDuration(150L).withEndAction { hide() }.start()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun TextView.makeLinks(vararg links: Pair<String, View.OnClickListener>) {
    val spannableString = SpannableString(this.text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
                textPaint.color = textPaint.linkColor
                // toggle below value to enable/disable
                // the underline shown below the clickable text
                textPaint.isUnderlineText = true
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second.onClick(view)
            }
        }
        startIndexOfLink = this.text.toString().indexOf(link.first, startIndexOfLink + 1)
        if(startIndexOfLink == -1) { continue }
        spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.movementMethod = LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
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

fun View.findLocationOfCenterOnTheScreen(): IntArray {
    val positions = intArrayOf(0, 0)
    getLocationInWindow(positions)
    // Get the center of the view
    positions[0] = positions[0] + width / 2
    positions[1] = positions[1] + height / 2
    return positions
}


fun View.dp(value: Float): Int {
    return if (value == 0f) 0 else ceil(context.resources.displayMetrics.density * value.toDouble()).toInt()
}

fun View.runOnUIThread(runnable: Runnable, delay: Long = 0) {
    if (delay == 0L) {
        App.applicationHandler.post(runnable)
    } else {
        App.applicationHandler.postDelayed(runnable, delay)
    }
}

fun View.show() { visibility = View.VISIBLE }
fun View.hide() { visibility = View.GONE }
fun View.invisible() { visibility = View.INVISIBLE }


/*** first imp this dependency on build.gradle :
 * implementation 'androidx.dynamicanimation:dynamicanimation:1.0.0' */
@SuppressLint("ClickableViewAccessibility")
fun View.implementSpringAnimationTrait(){
    val scaleXAnim = SpringAnimation(this,DynamicAnimation.SCALE_X,0.91f)
    val scaleYanim = SpringAnimation(this,DynamicAnimation.SCALE_Y,0.91f)

    setOnTouchListener { view, event ->

        when(event.action){
            MotionEvent.ACTION_DOWN ->{

                scaleXAnim.spring.stiffness = SpringForce.STIFFNESS_MEDIUM
                scaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                scaleXAnim.start()

                scaleYanim.spring.dampingRatio = SpringForce.STIFFNESS_MEDIUM
                scaleYanim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                scaleYanim.start()
            }

            MotionEvent.ACTION_UP , MotionEvent.ACTION_CANCEL ->{
                scaleXAnim.cancel()
                scaleYanim.cancel()

                val reverseScaleXAnim = SpringAnimation(this,DynamicAnimation.SCALE_X,1f)
                reverseScaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                reverseScaleXAnim.start()

                val reverseScaleYAnim = SpringAnimation(this,DynamicAnimation.SCALE_Y,1f)
                reverseScaleYAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleYAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                reverseScaleYAnim.start()
            }
        }
        false
    }
}



fun PopupMenu.insertMenuItemIcons(
    context: Context, ) {
    if (hasIcon()) for (i in 0 until menu.size()) insertMenuItemIcon(context, menu.getItem(i))
}

fun PopupMenu.hasIcon(): Boolean {
    for (i in 0 until menu.size()) if (menu.getItem(i).icon != null) return true
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

fun Context.copyToClipboard(text: String){
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.text = text
    } else {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText(getString(R.string.string_msg_clipboard_message), text))
    }
}

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

fun convertEnNumbersToPersianNumbers(faNumbers: String): String {
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
    for (num in mChars) changedVariable = faNumbers.replace(num[0], num[1])
    return faNumbers
}

fun isNetConnected(context: Context?): Boolean {
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

fun isInternetConnected(context: Context?): Boolean {
    val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun isAppAvailable(context: Context, appName: String): Boolean {
    val pm = context.packageManager
    return try {
        pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException){ false }
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

 fun turnOnGPS (activity: Activity) {
    val manager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER).not()) {
        // turnOnGPS
        val intent = Intent("android.location.GPS_ENABLED_CHANGE")
        intent.putExtra("enabled", true)
        activity.sendBroadcast(intent)
    }
}

fun setStatusBarColor(window: Window, colorId : Int) {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = App.context?.resources!!.getColor(colorId,null)
    }
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

fun hideSystemUI(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    val controllerCompat = WindowInsetsControllerCompat(window, window.decorView)
    controllerCompat.hide(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.navigationBars())
    controllerCompat.systemBarsBehavior =
        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

fun showSystemUI(window: Window) {
    val wic = WindowInsetsControllerCompat(window, window.decorView)
    wic.isAppearanceLightStatusBars = true
    // And then you can set any background color to the status bar.
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, window.decorView).show(WindowInsetsCompat.Type.systemBars())
}

fun checkPermission(activity: Activity , permission: String, requestCode: Int) {
    if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
        // Requesting the permission
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    } else {
        Toast.makeText(activity, "Permission already granted", Toast.LENGTH_SHORT).show()
    }
}

fun NavController.navigateSafe(direction: NavDirections, navOptions: NavOptions? = null) {
    currentDestination?.getAction(direction.actionId)?.let {
        navigate(direction.actionId, direction.arguments, navOptions)
    }
}


fun isMobile(number: String): Boolean {
    return number.isNotEmpty() && number.matches("09\\d{9}".toRegex())
}

fun isPhoneNumber(number: String): Boolean {
    return number.matches("\\+?\\d(-|\\d)+".toRegex())
}

fun isUrl(url: String): Boolean {
    return android.util.Patterns.WEB_URL.matcher(url).matches();
}

//check text is persian
fun isPersian(string: String): Boolean {
    val pattern = "^[\\s\\u0621\\u0622\\u0627\\u0623\\u0628\\u067e\\u062a\\u062b\\u062c\\u0686\\u062d\\u062e\\u062f\\u0630\\u0631\\u0632\\u0698\\u0633-\\u063a\\u0641\\u0642\\u06a9\\u06af\\u0644-\\u0646\\u0648\\u0624\\u0647\\u06cc\\u0626\\u0625\\u0671\\u0643\\u0629\\u064a\\u0649]+"
    return string.matches(pattern.toRegex())
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



fun String.extractNumberInString() = replace("\\D+".toRegex(),"").toInt()

fun String.getSeparatorAmountFormat(): String {
    return try {
        val decimalFormat = DecimalFormat()
        val decimalFormatSymbol = DecimalFormatSymbols()
        decimalFormatSymbol.groupingSeparator = ','
        decimalFormat.decimalFormatSymbols = decimalFormatSymbol
        decimalFormat.format(if (this.isNotEmpty()) this.toLong() else "0".toLong())
    }
    catch (ex: java.lang.Exception) { this.toString() }
}

fun String.getMoneyFormat(): String {
    if (this.isEmpty())
        return ""
    var result = this

    try {
        val formatter: NumberFormat = DecimalFormat("#,###")
        if (result.contains(",") || result.contains('٬') || result.contains("،")) {
            result = result.replace(",", "")
            result = result.replace("٬", "")
            result = result.replace("،", "")
        }
        result = formatter.format(result.toLong())
        result = result.getPersianNumber()
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
    }
    return result
}

fun String.getPersianNumber(): String {
    var number = this
    try {
        val persianNumbers = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        for (i in 0..9) number = number.replace(i.toString(), persianNumbers[i])

    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return number
}

fun Double.formatForQta(): String {
    val floatString = this.toString()
    val decimalString: String = floatString.substring(floatString.indexOf('.') + 1, floatString.length)

    return when (decimalString.toInt() == 0) {
        true -> this.toInt().toString()
        false -> "%.3f".format(this)
    }
}

fun getStandardPhoneNumber(mobileNumber: String): String {
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

fun convertDpToPx(dp : Float, context : Context?) : Float {
    return if (context != null){
        val resource = context.resources
        val metrics = resource.displayMetrics
        dp / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }else{
        val metrics = Resources.getSystem().displayMetrics
        dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}

fun convertDpiToPixel(dp: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
fun addTurnScreenOnAlwaysFlag(window : Window){
    window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
}
fun clearTurnScreenOnAlwaysFlag(window : Window){
    window.clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
}
fun showToast(ctx : Context , message : String){
    Toast.makeText(ctx,message.trim() , Toast.LENGTH_LONG).show()
}

fun convertTimeToMilliSecond(time: Long): Long {
    return time * 1000L
}

fun convertTimeToSecond(time: Long): Long {
    return time / 1000L
}

fun KClass<*>.simpleClassName():String{
    // returned -> ClassName -> className
    return this.toString().split(".").last().convertPascalCaseToCamelCase()
}
fun String.convertPascalCaseToCamelCase():String{
    return this.replaceFirst(this.substring(0,1),this.substring(0,1).toLowerCase())
}


fun Activity.keyboardListener(
    view: View,
    lifecycleOwner: Lifecycle,
    callBack: (keyboardIsOpen: Boolean) -> Unit
) {
    var isKeyboardOpenLastState = false
    var isKeyboardOpen = false
    Log.i(TAG, "handleKeyboardAndAnimation: ")
    view.viewTreeObserver.addOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener {
        Log.i(
            TAG,
            "onGlobalLayout: ======================================================================================================"
        )
        val layoutRect = Rect()
        var translationYAnimator: ObjectAnimator? = null
        //r will be populated with the coordinates of your view that area still visible.
        view.getWindowVisibleDisplayFrame(layoutRect)
        val statusBarHeight = layoutRect.top
        Log.i(TAG, "onGlobalLayout: statusBarHeight => $statusBarHeight")
//        isKeyboardOpenLastState = isKeyboardOpen

        ///////////////////////////// get focus view ////////////////////////////////////////
        val focusedView: View?
        val focusedViewRect = Rect()
        var focusedViewHeight = 0
        var focusedViewY = 0
        var heightDiff = 0
        heightDiff = view.rootView.height - (layoutRect.bottom - layoutRect.top)
        focusedView = currentFocus
        if (focusedView != null) {
            Log.i(TAG, "onGlobalLayout: focusedView is not null => " + focusedView.id)
            focusedView.getWindowVisibleDisplayFrame(focusedViewRect)
            focusedViewHeight = focusedView.height
            Log.i(TAG, "onGlobalLayout: focusedViewHeight => $focusedViewHeight")
            val locationWin = IntArray(2)
            focusedView.getLocationInWindow(locationWin)
            focusedViewY = locationWin[1]
            Log.i(TAG, "onGlobalLayout: focusedViewY => $focusedViewY")
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
//        if (isKeyboardOpenLastState==isKeyboardOpen)
//            return@OnGlobalLayoutListener

        if (heightDiff > 0.25 * view.rootView.height) { // if more than 25% of the screen, its probably a keyboard...
            Log.i(TAG, "onGlobalLayout: keyboard opened")
            isKeyboardOpen = true
        } else {
            Log.i(TAG, "onGlobalLayout: keyboard closed")
            isKeyboardOpen = false
        }

        if (isKeyboardOpen == isKeyboardOpenLastState)
            return@OnGlobalLayoutListener


        if (lifecycleOwner.currentState.isAtLeast(Lifecycle.State.STARTED))
            callBack.invoke(isKeyboardOpen)

        isKeyboardOpenLastState = isKeyboardOpen
    })
}


fun AppBarLayout.offsetChangeListener(
    callBack: (
        offset: Int,
        percent: Float,
        anchorHeight: Int,
        maxScrollRange: Float
    ) -> Unit
) {
    Log.i("offsetChangeListener", "offsetChangeListener: ")
    var offset = 0
    var correctHeight = 0f
    var percent = 0f
    var lastOffset = -1
    addOnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (lastOffset == verticalOffset)
            return@addOnOffsetChangedListener

        lastOffset = verticalOffset

        val maxScroll = appBarLayout.totalScrollRange.toFloat()
        offset = abs(verticalOffset)
        percent =
            if (offset != 0)
                (offset / maxScroll)
            else
                0f

        // used for anchor view
        correctHeight = abs((percent * height) - height)


        Log.i("offsetChangeListener", "offset: $offset percent: $percent")
        callBack.invoke(
            offset,
            percent,
            correctHeight.toInt(),
            maxScroll
        )
    }
}

inline fun <T : Any , R> T?.withNotNull(block : (T) -> R) : R? {
    return this?.let(block)
    // means -> if(B != null) { run this code block }
}


fun Context.isDarkThemeOn(context: Context): Boolean {
    Log.i("TAG", "isDarkThemeOn: ${context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK}")
    return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        /* 32 */ Configuration.UI_MODE_NIGHT_YES -> true
        /* 16 */ Configuration.UI_MODE_NIGHT_NO -> false
        /* 0 */ Configuration.UI_MODE_NIGHT_UNDEFINED -> false
        else -> false
    }
}


fun Context.isEnabledDarkMode() : Boolean{
    val darkModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK// Retrieve the Mode of the App.
    return darkModeFlags == Configuration.UI_MODE_NIGHT_YES //Check if the Dark Mode is On
}

fun Context.switchToDarkModeIfNeeded(){
    Log.i(TAG, "switchToDarkModeIfNeeded - dakMode is ${this.isEnabledDarkMode()}")
    if (this.isEnabledDarkMode()) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) //Switch on the dark mode.
    }
}

fun Context.switchToLightModeIfNeeded(){
    Log.i(TAG, "switchToLightModeIfNeeded - dakMode is ${this.isEnabledDarkMode()}")
    if (!this.isEnabledDarkMode()) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //Switch off the dark mode.
    }
}

fun changeTheme(isDarkTheme: Boolean) {
    Log.i(TAG, "changeTheme function called , isDarkTheme is $isDarkTheme")
    if (isDarkTheme) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
}



open class AnimatorListenerImpl : Animator.AnimatorListener {
    override fun onAnimationStart(animation: Animator) {}
    override fun onAnimationEnd(animation: Animator) {}
    override fun onAnimationCancel(animation: Animator) {}
    override fun onAnimationRepeat(animation: Animator) {}
}


fun enableOrDisableThisViews(vararg views : View,enable : Boolean){
    for (i in views){
        i.isEnabled = enable
        i.isClickable = enable
        i.isFocusable = enable
    }
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





fun ViewBinding.setView(view: Any){
    try{ javaClass.getMethod("setView", view.javaClass).invoke(this,view)
    }catch (e: Exception){ /**e.printStackTrace()*/ }
}
fun ViewBinding.setVm(vm: Any){
    try{ javaClass.getMethod("setVm", vm.javaClass).invoke(this,vm)
    }catch (e: Exception){ /**e.printStackTrace()*/ }
}





// Singleton Data Store
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")












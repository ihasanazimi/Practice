//package ir.ha.myapplication.utility
//
//import android.animation.*
//import android.content.Context
//import android.graphics.Color
//import android.graphics.PorterDuff
//import android.graphics.PorterDuffColorFilter
//import android.graphics.drawable.ColorDrawable
//import android.graphics.drawable.Drawable
//import android.text.InputType
//import android.text.SpannableStringBuilder
//import android.text.style.ImageSpan
//import android.view.MenuItem
//import android.view.View
//import android.view.inputmethod.InputMethodManager
//import android.widget.EditText
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.PopupMenu
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import ir.ha.myapplication.R
//import kotlin.math.ceil
//
//
//
//fun View.show() {
//    visibility = View.VISIBLE
//}
//
//fun View.hide() {
//    visibility = View.GONE
//}
//
//fun View.invisible() {
//    visibility = View.INVISIBLE
//}
//
//
//fun AppCompatActivity.showFragment(
//    fragment: Fragment,
//    tag: String,
//    addToBackStack: Boolean,
//    customAnimations: Boolean,
//    containerViewId: Int,
//    commitAllowingStateLoss: Boolean = false
//) {
//    val fragmentTransaction = supportFragmentManager
//        .beginTransaction()
//
//
//
//    if (customAnimations) {
//        fragmentTransaction.setCustomAnimations(
//            R.anim.enter_from_right,
//            R.anim.exit_to_left,
//            R.anim.enter_from_left,
//            R.anim.exit_to_right
//        )
//    }
//
//
//    if (addToBackStack) {
//        fragmentTransaction.addToBackStack(tag)
//    }
//    fragmentTransaction.add(containerViewId, fragment, tag)
//    if (commitAllowingStateLoss) {
//        fragmentTransaction.commitAllowingStateLoss()
//    } else {
//        fragmentTransaction.commit()
//    }
//}
//
//fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
//    isFocusable = !value
//    isFocusableInTouchMode = !value
//    this.inputType = inputType
//}
//
//fun View.getDrawable(drawableResID: Int): Drawable? =
//    ContextCompat.getDrawable(context, drawableResID)?.mutate()
//
//fun View.getColor(colorResID: Int): Int =
//    ContextCompat.getColor(context, colorResID)
//
//fun Fragment.getDrawable(drawableResID: Int): Drawable? =
//    ContextCompat.getDrawable(requireContext(), drawableResID)?.mutate()
//
//fun Fragment.getColor(colorResID: Int): Int =
//    ContextCompat.getColor(requireContext(), colorResID)
//
//fun AppCompatActivity.getDrawable(drawableResID: Int): Drawable? =
//    ContextCompat.getDrawable(this, drawableResID)?.mutate()
//
//fun AppCompatActivity.getColor(colorResID: Int): Int =
//    ContextCompat.getColor(this, colorResID)
//
//fun EditText.customRequestFocus() {
//    requestFocus()
//    val imm: InputMethodManager =
//        context.getSystemService(Context.INPUT_METHOD_SERVICE)
//                as InputMethodManager
//    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
//}
//
//fun View.getColoredDrawable(drawableResID: Int, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
//    val drawable = ContextCompat.getDrawable(context, drawableResID)?.mutate()
//    val colorFilter = PorterDuffColorFilter(
//        ContextCompat.getColor(
//            context,
//            colorResID
//        ), mode
//    )
//    drawable?.colorFilter = colorFilter
//    return drawable
//}
//
//fun View.getColoredDrawable(drawable: Drawable, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
//    val colorFilter = PorterDuffColorFilter(
//        ContextCompat.getColor(
//            context,
//            colorResID
//        ), mode
//    )
//    drawable.colorFilter = colorFilter
//    return drawable
//}
//
//fun View.shakeView(x: Float, num: Int, finish: Boolean = false) {
//
//    if (finish) {
//        translationX = 0f
//    }
//    val animatorSet = AnimatorSet()
//    val valueAnimator = ObjectAnimator.ofFloat(
//        this,
//        "translationX",
//        dp(x).toFloat()
//    )
//    animatorSet.playTogether(
//        valueAnimator
//    )
//    valueAnimator.repeatCount = ValueAnimator.INFINITE
//    animatorSet.duration = 50
//    animatorSet.addListener(object : AnimatorListenerAdapter() {
//        override fun onAnimationEnd(animation: Animator) {
//            if (!finish)
//                shakeView(if (num == 5) 0f else -x, num + 1)
//        }
//    })
//    animatorSet.start()
//}
//
//
//fun View.dp(value: Float): Int {
//    return if (value == 0f) {
//        0
//    } else ceil(context.resources.displayMetrics.density * value.toDouble()).toInt()
//}
//
//
//
//fun PopupMenu.insertMenuItemIcons(
//    context: Context,
//) {
//    if (hasIcon()) {
//        for (i in 0 until menu.size()) {
//            insertMenuItemIcon(context, menu.getItem(i))
//        }
//    }
//}
//
//fun PopupMenu.hasIcon(): Boolean {
//    for (i in 0 until menu.size()) {
//        if (menu.getItem(i).icon != null) return true
//    }
//    return false
//}
//
//fun PopupMenu.insertMenuItemIcon(context: Context, menuItem: MenuItem) {
//    var icon: Drawable? = menuItem.icon
//    if (icon == null) icon = ColorDrawable(Color.TRANSPARENT)
//    val iconSize: Int =
//        context.resources.getDimensionPixelSize(R.dimen.menu_item_icon_size)
//    icon.setBounds(0, 0, iconSize, iconSize)
//    val imageSpan = ImageSpan(icon)
//    val ssb = SpannableStringBuilder("     " + menuItem.title)
//    ssb.setSpan(imageSpan, 1, 2, 0)
//    menuItem.title = ssb
//    menuItem.icon = null
//}
//
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
//
//fun AppCompatActivity.runOnUIThread(runnable: Runnable, delay: Long = 0) {
//    if (delay == 0L) {
//        ApplicationLoader.applicationHandler.post(runnable)
//    } else {
//        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
//    }
//}
//
//fun View.runOnUIThread(runnable: Runnable, delay: Long = 0) {
//    if (delay == 0L) {
//        ApplicationLoader.applicationHandler.post(runnable)
//    } else {
//        ApplicationLoader.applicationHandler.postDelayed(runnable, delay)
//    }
//}
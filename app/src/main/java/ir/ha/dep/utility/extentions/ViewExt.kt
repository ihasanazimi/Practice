package ir.ha.dep.utility.extentions

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ir.ha.dep.App
import kotlin.math.ceil

fun View.show() { visibility = View.VISIBLE }

fun View.hide() { visibility = View.GONE }

fun View.invisible() { visibility = View.INVISIBLE }



/** get resources */
fun View.getDrawable(drawableResID: Int): Drawable? =
    ContextCompat.getDrawable(context, drawableResID)?.mutate()

fun View.getColor(colorResID: Int): Int =
    ContextCompat.getColor(context, colorResID)




fun EditText.customRequestFocus() {
    requestFocus()
    val imm: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.setReadOnly(value: Boolean, inputType: Int = InputType.TYPE_NULL) {
    isFocusable = !value
    isFocusableInTouchMode = !value
    this.inputType = inputType
}


fun RecyclerView.scrollToTop() {
    if(canScrollVertically(-1))
        smoothScrollToPosition(0)
}



fun View.getColoredDrawable(drawableResID: Int, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val drawable = ContextCompat.getDrawable(context, drawableResID)?.mutate()
    val colorFilter = PorterDuffColorFilter(
        ContextCompat.getColor(
            context,
            colorResID
        ), mode
    )
    drawable?.colorFilter = colorFilter
    return drawable
}



fun View.shakeView(x: Float, num: Int, finish: Boolean = false) {

    if (finish) {
        translationX = 0f
    }
    val animatorSet = AnimatorSet()
    val valueAnimator = ObjectAnimator.ofFloat(
        this,
        "translationX",
        dp(x).toFloat()
    )
    animatorSet.playTogether(
        valueAnimator
    )
    valueAnimator.repeatCount = ValueAnimator.INFINITE
    animatorSet.duration = 50
    animatorSet.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            if (!finish)
                shakeView(if (num == 5) 0f else -x, num + 1)
        }
    })
    animatorSet.start()
}



fun View.getColoredDrawable(drawable: Drawable, colorResID: Int, mode: PorterDuff.Mode): Drawable? {
    val colorFilter = PorterDuffColorFilter(
        ContextCompat.getColor(
            context,
            colorResID
        ), mode
    )
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





fun EditText.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
            InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.showKeyboard() {
    this.requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}



/**
 * first imp this dependency on build.gradle :
 * implementation 'androidx.dynamicanimation:dynamicanimation:1.0.0'
 */
fun View.implementSpringAnimationTrait(){
    val scaleXAnim = SpringAnimation(this,DynamicAnimation.SCALE_X,0.91f)
    val scaleYanim = SpringAnimation(this,DynamicAnimation.SCALE_Y,0.91f)

    setOnTouchListener { view, event ->

        Log.i("setOnTouchListener", "implementSpringAnimationTrait: " + event.action.toString()) // log for test (print event actions)

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




fun View.dp(value: Float): Int {
    return if (value == 0f) {
        0
    } else ceil(context.resources.displayMetrics.density * value.toDouble()).toInt()
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


fun addTurnScreenOnAlwaysFlag(window : Window){
    window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
}

fun clearTurnScreenOnAlwaysFlag(window : Window){
    window.clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
}


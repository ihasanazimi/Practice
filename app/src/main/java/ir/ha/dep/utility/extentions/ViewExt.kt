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
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.recyclerview.widget.RecyclerView
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
@SuppressLint("ClickableViewAccessibility")
fun View.springAnimImpl(){
    val scaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_X,0.9f)
    val scaleYanim = SpringAnimation(this, DynamicAnimation.SCALE_Y,0.9f)

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
                val reverseScaleXAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y,0.9f)
                reverseScaleXAnim.spring.stiffness = SpringForce.STIFFNESS_LOW
                reverseScaleXAnim.spring.dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                val reverseScaleYAnim = SpringAnimation(this, DynamicAnimation.SCALE_Y,0.9f)
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

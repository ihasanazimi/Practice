package ir.ha.myapplication.utility.extentions

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
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

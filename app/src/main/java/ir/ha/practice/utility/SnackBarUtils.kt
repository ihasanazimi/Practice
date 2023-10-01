package ir.ha.practice.utility


import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import ir.ha.practice.R
import java.lang.ref.WeakReference


object SnackBarUtils  {

        const val progressDuration = 5000
        private var snackbar: Snackbar? = null
        private lateinit var context: WeakReference<Context>

        val TAG = SnackBarUtils::class.java.simpleName




        fun showSnackBar(
            context: WeakReference<Context>,
            message: String,
            @DrawableRes icon: Int,
            layout: Int = R.layout.layout_snack_bar,
            hasAction:Boolean = false,
            callback: (() -> Unit)? = null
        ) {
            Log.d(TAG, "showSnackBar: ")
            this.context = context
            if (context == null) return

            val view: View = LayoutInflater.from(context.get()).inflate(layout, null)
            (view.findViewById(R.id.tvMessage) as TextView).text = message
            (view.findViewById(R.id.ivSnackIcon) as ImageView).setImageResource(icon)
//            if(hasAction){
//                (view.findViewById(R.id.tvUnderstand) as TextView).visibility = View.VISIBLE
//            }else (view.findViewById(R.id.tvUnderstand) as TextView).visibility = View.GONE


            view.animation =
                AnimationUtils.loadAnimation(context.get(), com.airbnb.lottie.R.anim.abc_slide_in_top)
            (context.get() as Activity).findViewById<View>(android.R.id.content).apply {
                makeSnackBar(this, message, hasAction,view,callback)
            }


        }



        private fun makeSnackBar(view: View, message: String, hasAction: Boolean,
                                 customView:View,callback: (() -> Unit)? = null) {
            Log.d(TAG, "makeSnackBar: ")
            val padding: Int = 0.toPx().toInt()
            snackbar = when(hasAction){
                true -> Snackbar.make(view, message,  Snackbar.LENGTH_INDEFINITE)
                else -> Snackbar.make(view, message,  Snackbar.LENGTH_SHORT)
            }

            snackbar?.let {
                val snackbarView = it.view
                val snackbarLayout = snackbarView as Snackbar.SnackbarLayout
                val snackContentLayout =
                    snackbarLayout.getChildAt(0) as SnackbarContentLayout
                val pLeft = snackbarLayout.paddingLeft
                val pRight = snackbarLayout.paddingRight
                snackbarLayout.setBackgroundColor(Color.TRANSPARENT)
                snackbarLayout.setPadding(0, 0, 0, 0)
                snackContentLayout.setPadding(pLeft, 0, pRight, 0)
                if (padding > 0) {
                    snackbarLayout.setPadding(padding, 0, padding, padding)
                }
                val params = snackbarView.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.TOP
                params.topMargin = (getActionBarHeight() - 40)
                it.view.layoutParams = params
                it.behavior = null
                snackbarLayout.addView(customView)
                it.show()
//                customView.tvUnderstand.setOnClickListener {view->
//                    it.dismiss()
//                    callback?.invoke()
//                }
            }


        }


        private fun Int.toPx(): Float {
            return this * (context.get()?.resources?.displayMetrics?.density ?: 0f)
        }

    private fun getActionBarHeight():Int{
        val tv = TypedValue()
        var actionBarHeight = 0
        if (context.get()?.theme?.resolveAttribute(android.R.attr.actionBarSize, tv, true)!!) {
             actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.get()?.resources?.displayMetrics)

        }
        return actionBarHeight / 2
    }




    }




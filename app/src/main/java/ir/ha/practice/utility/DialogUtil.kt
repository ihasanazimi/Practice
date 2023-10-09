package ir.ha.practice.utility

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import ir.ha.practice.R
import java.lang.ref.WeakReference

object DialogUtil {


    private val TAG = DialogUtil::class.java.simpleName
    private var dialog: Dialog? = null
    var status = false

    @SuppressLint("MissingInflatedId")
    fun showInfoDialog(
        context: WeakReference<Context>,
        title: String,
        message: String,
        cancelBtnTitle: String = context.get()!!.getString(R.string.no),
        okBtnTitle: String = context.get()!!.getString(R.string.yes),
        isSingleBtn: Boolean = false,
        image: String? = null,
        isTouchOutsideEnable: Boolean = true,
        dismissCallBack:(() -> Unit?)?=null,
        callback: ((status: Boolean) -> Unit)? = null
    ) {

        Log.d(TAG, "showInfoDialog: ")

        dismissDialog()
        if (context.get() == null) return

        val view: View = LayoutInflater.from(context.get()!!).inflate(R.layout.layout_alert_dialog, null)
        (view.findViewById(R.id.titleTV) as TextView).text = title
        (view.findViewById(R.id.descriptionTV) as TextView).text = message
        image?.let {
            Glide.with(context.get()!!).load(it).into((view.findViewById(R.id.dialogImageView) as ImageView))
        } ?: kotlin.run {
            (view.findViewById(R.id.imageViewContainer) as MaterialCardView).visibility = View.GONE
        }

        val primaryBtn: MaterialButton = view.findViewById(R.id.okBtn)
        val secondaryBtn: MaterialButton = view.findViewById(R.id.cancelBtn)

        //text button title
        primaryBtn.text = okBtnTitle
        secondaryBtn.text = cancelBtnTitle

        secondaryBtn.setOnClickListener {
            Log.i(TAG, "showInfoDialog: cancel button ")
            status = false
            dismissDialog()
        }
        primaryBtn.setOnClickListener {
            Log.i(TAG, "showInfoDialog: ok button ")
            status = true
            dismissDialog()
        }

        /*val closeIcon: ImageView = view.findViewById(R.id.closeIv)
        closeIcon.setOnClickListener {
            Log.d(TAG, "showInfoDialog: close button")
            status = false
            dismissDialog()
        }*/

        if (isSingleBtn) secondaryBtn.visibility = View.GONE

//        ViewCompat.setLayoutDirection(view, ViewCompat.LAYOUT_DIRECTION_RTL)

        dialog = Dialog(context.get()!!)
        showDialog(view, isTouchOutsideEnable)

        dialog!!.setOnDismissListener {
            Log.i(TAG, "showInfoDialog: dismiss listener ")
            callback?.invoke(status)
        }

        dialog!!.setOnCancelListener {
            Log.i(TAG, "showInfoDialog: cancel")
            status = false
            dismissDialog()
        }
    }


    private fun showDialog(view: View, isTouchOutsideEnable: Boolean = true) {
        Log.d(TAG, "showDialog: ")
        if (dialog != null) {
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.setContentView(view)
            dialog!!.setCanceledOnTouchOutside(isTouchOutsideEnable)
            dialog!!.setCancelable(isTouchOutsideEnable)
            val windowLayout: WindowManager.LayoutParams = dialog!!.window!!.attributes
            windowLayout.gravity = Gravity.CENTER
            dialog!!.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog!!.show()
        }
    }


    fun dismissDialog() {
        Log.d(TAG, "dismissDialog: ")
        try {
            dialog?.dismiss()
            dialog = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}
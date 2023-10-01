package ir.ha.practice.utility.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.ha.practice.R
import ir.ha.practice.utility.SnackBarUtils
import ir.ha.practice.utility.util.localizedContext
import java.lang.ref.WeakReference

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    private var _binding: V? = null
    val binding get() = _binding!!

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,layoutId)
        initWhenUiCreated()
        clickEvents()
    }

    open fun clickEvents(){}
    open fun initWhenUiCreated(){}

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun showErrorMessage(message: String) {
        Log.e("TAG", "showErrorMessage: Error")
        SnackBarUtils.showSnackBar(
            WeakReference(this), message, R.drawable.baseline_error_outline_24
        )
    }

    fun showMessage(message: String, icon: Int = R.drawable.baseline_done_24) {
        Log.e("TAG", "showMessage: ")
        SnackBarUtils.showSnackBar(
            WeakReference(this), message, icon
        )
    }
}
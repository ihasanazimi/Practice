package ir.ha.dummy.utility.base

import android.content.Context
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.ha.dummy.utility.localizedContext

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: ViewGroup?
        get() = window.decorView.rootView as ViewGroup
    override val viewContext: Context?
        get() = this

    fun <T : ViewDataBinding?> getBinding(layoutID: Int): T {
        return DataBindingUtil.setContentView(this, layoutID)
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }
}
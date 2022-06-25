package ir.ha.dep.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable
import ir.ha.dep.R


abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: ViewGroup?
        get() = view as ViewGroup?
    override val viewContext: Context?
        get() = context


    fun <T : ViewDataBinding?> getBinding(layout: Int, parent: ViewGroup): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, parent, false)
    }
}


abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: ViewGroup?
        get() = window.decorView.rootView as ViewGroup
    override val viewContext: Context?
        get() = this

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.setContentView(this, layout)
    }
}



abstract class BaseBottomSheetDialogFrg: BottomSheetDialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.rootView as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}



abstract class BaseDialogFrg : DialogFragment() , BaseView {

    override val rootView: ViewGroup?
        get() = dialog?.window?.decorView?.rootView as ViewGroup
    override val viewContext: Context?
        get() = this.requireContext()

    fun <T : ViewDataBinding?> getBinding(layout: Int): T {
        return DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layout, null, false)
    }

}



interface BaseView {

    val rootView: ViewGroup?
    val viewContext: Context?

    fun setProgressIndicator(mustShow: Boolean) {
        rootView.let {
            viewContext.let { context ->
                var loadingBar = it?.findViewById<View>(R.id.loadingView)
                if (loadingBar == null && mustShow) {
                    loadingBar = LayoutInflater.from(context).inflate(R.layout.loading_bar, it, false) as FrameLayout?
                    rootView?.addView(loadingBar)
                }
                rootView?.visibility = if (mustShow) View.VISIBLE else View.GONE
            }
        }
    }
}


abstract class BaseViewModel : ViewModel() {
    open val composable = CompositeDisposable()
        get() = field


    override fun onCleared() {
        composable.clear()
        super.onCleared()
    }
}
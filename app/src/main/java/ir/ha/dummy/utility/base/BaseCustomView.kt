package ir.ha.dummy.utility.base

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import ir.ha.dummy.utility.extentions.setView

abstract class BaseCustomView<B : ViewDataBinding> : RelativeLayout {

    @LayoutRes
    abstract fun layout(): Int

    lateinit var binding: B

    constructor(context: Context?) : super(context) {
        init(null, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, 0, 0)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs, defStyleAttr, 0)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(layout(), this, true)
            return
        }
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layout(), this, true)

        binding.setView(this)

    }


}
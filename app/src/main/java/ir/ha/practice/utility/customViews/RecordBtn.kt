package ir.ha.practice.utility.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View


class RecordBtn : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)


    fun dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()
}
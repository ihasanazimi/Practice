package ir.ha.dummy.utility.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HorizontalRecyclerView : RecyclerView {

    private val Y_BUFFER = 10
    private var preX = 0f
    private var preY = 0f

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context!!, attrs, defStyle) {
        init()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    fun init() {
        val linearlayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        layoutManager = linearlayoutManager

        addOnItemTouchListener(object : OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                when (e.action) {
                    MotionEvent.ACTION_DOWN -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    MotionEvent.ACTION_MOVE -> {
                        if (Math.abs(e.x - preX) > Math.abs(e.y - preY)) {
                            rv.parent.requestDisallowInterceptTouchEvent(true)
                        } else if (Math.abs(e.y - preY) > Y_BUFFER) {
                            rv.parent.requestDisallowInterceptTouchEvent(false)
                        }

                    }
                }
                preX = e.x
                preY = e.y
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, event: MotionEvent) {
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

    }
}
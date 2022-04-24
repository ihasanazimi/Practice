package ir.ha.myapplication.utility

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.scrollToTop() {
    if(canScrollVertically(-1))
        smoothScrollToPosition(0)
}
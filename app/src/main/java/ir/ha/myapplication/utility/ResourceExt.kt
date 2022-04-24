package ir.ha.myapplication.utility

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

fun Context.drawable(@DrawableRes drawableRes: Int) =
    ResourcesCompat.getDrawable(resources, drawableRes, theme)
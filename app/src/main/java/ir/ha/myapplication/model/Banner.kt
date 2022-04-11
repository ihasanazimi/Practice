package ir.ha.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner(val ImageUrl : String, val title : String) : Parcelable
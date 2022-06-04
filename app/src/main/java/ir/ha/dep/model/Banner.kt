package ir.ha.dep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Banner(val ImageUrl : String, val title : String) : Parcelable
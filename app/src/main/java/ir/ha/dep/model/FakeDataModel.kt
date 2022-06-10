package ir.ha.dep.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FakeDataModel(val fileName : String, val imageUrl : String) : Parcelable

package ir.ha.practice.model.entities

import android.os.Parcel
import android.os.Parcelable

data class BannerSliderEntity(
    val imageUrl : String,
    val title : String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrl)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BannerSliderEntity> {
        override fun createFromParcel(parcel: Parcel): BannerSliderEntity {
            return BannerSliderEntity(parcel)
        }

        override fun newArray(size: Int): Array<BannerSliderEntity?> {
            return arrayOfNulls(size)
        }
    }
}
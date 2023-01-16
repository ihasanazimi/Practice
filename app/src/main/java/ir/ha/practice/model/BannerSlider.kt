package ir.ha.practice.model

import android.os.Parcel
import android.os.Parcelable

data class BannerSlider(val ImageUrl : String, val title : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ImageUrl)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BannerSlider> {
        override fun createFromParcel(parcel: Parcel): BannerSlider {
            return BannerSlider(parcel)
        }

        override fun newArray(size: Int): Array<BannerSlider?> {
            return arrayOfNulls(size)
        }
    }
}
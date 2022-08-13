package ir.ha.dep.model

import android.os.Parcel
import android.os.Parcelable


data class FakeDataModel(val fileName : String, val imageUrl : String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fileName)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FakeDataModel> {
        override fun createFromParcel(parcel: Parcel): FakeDataModel {
            return FakeDataModel(parcel)
        }

        override fun newArray(size: Int): Array<FakeDataModel?> {
            return arrayOfNulls(size)
        }
    }
}

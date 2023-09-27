package ir.ha.practice.model.entities

import android.os.Parcel
import android.os.Parcelable


data class FakeEntity(
    val fileName : String,
    val imageUrl : String)
    : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<FakeEntity> {
        override fun createFromParcel(parcel: Parcel): FakeEntity {
            return FakeEntity(parcel)
        }

        override fun newArray(size: Int): Array<FakeEntity?> {
            return arrayOfNulls(size)
        }
    }
}

package ir.ha.dep.repo

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
class Contact():Parcelable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var uid : Long? = null
    @ColumnInfo
    var fName : String? = null
    @ColumnInfo
    var lName : String? = null

    constructor(fName : String , lName : String) : this(){
        this.fName = fName
        this.lName = lName
    }
    constructor(uid : Long , fName : String , lName : String) : this(){
        this.uid = uid
        this.fName = fName
        this.lName = lName
    }

companion object{
    fun fullName(contact : Contact) : String {
        return contact.fName + " " + contact.lName
    }
}
}
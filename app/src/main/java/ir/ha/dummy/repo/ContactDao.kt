package ir.ha.dummy.repo

import androidx.room.*
import ir.ha.dummy.model.ContactModel

@Dao
interface ContactDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertContact(contactModel: ContactModel) : Long

    @Delete
    fun deleteContact(contactModel: ContactModel) : Int

    @Update
    fun updateContact(contactModel  : ContactModel)

    @Query("select * from ContactModel")
    fun allContacts() : List<ContactModel>

}
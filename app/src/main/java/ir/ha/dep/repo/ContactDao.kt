package ir.ha.dep.repo

import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertContact(contactModel: ContactModel) : Long

    @Delete
    fun deleteContact(contactModel: ContactModel) : Int

    @Update
    fun updateContact(contactModel  :ContactModel)

    @Query("select * from ContactModel")
    fun allContacts() : List<ContactModel>

}
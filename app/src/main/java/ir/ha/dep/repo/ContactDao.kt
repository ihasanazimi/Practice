package ir.ha.dep.repo

import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertContact(item: Contact) : Long

    @Delete
    fun deleteContact(item: Contact) : Int

    @Update
    fun updateContact(item  :Contact)

    @Query("select * from Contact")
    fun allContacts() : List<Contact>

}
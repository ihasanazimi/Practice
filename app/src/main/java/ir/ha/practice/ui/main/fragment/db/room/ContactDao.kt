package ir.ha.practice.ui.main.fragment.db.room

import androidx.room.*
import ir.ha.practice.ui.main.fragment.db.room.entity.User

@Dao
interface ContactDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertContact(user: User) : Long

    @Delete
    fun deleteContact(user: User) : Int

    @Update
    fun updateContact(user  : User)

    @Query("select * from User")
    fun getAllUser() : List<User>

}
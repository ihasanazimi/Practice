package ir.ha.practice.ui.components.local_data_base.room.dao

import androidx.room.*
import ir.ha.practice.model.entities.PersonEntity

@Dao
interface ContactDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertContact(personEntity: PersonEntity) : Long

    @Delete
    fun deleteContact(personEntity: PersonEntity) : Int

    @Update
    fun updateContact(personEntity  : PersonEntity)

    @Query("select * from PersonEntity")
    fun getAllUser() : List<PersonEntity>

}
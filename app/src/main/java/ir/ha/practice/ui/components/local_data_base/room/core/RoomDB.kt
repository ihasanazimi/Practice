package ir.ha.practice.ui.components.local_data_base.room.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.ha.practice.data.entities.PersonEntity
import ir.ha.practice.ui.components.local_data_base.room.dao.ContactDao


@Database(
    entities = [PersonEntity::class], version = 4, exportSchema = false)


abstract class RoomDB : RoomDatabase() {

    // Dao
    abstract fun userDao(): ContactDao

    companion object {
        @Volatile //access just one there on main thread!
        var database: RoomDB? = null
        // singleTon design pattern
        fun getDataBase(context: Context): RoomDB {
            val tempInstance = database
            if (database != null) return tempInstance as RoomDB
            //synchronized  -->  means -->  access just one there on main thread!
            synchronized(this) {
                val instance = Room.databaseBuilder(context, RoomDB::class.java, "database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
                database = instance
                return instance
            }
        }
    }
}
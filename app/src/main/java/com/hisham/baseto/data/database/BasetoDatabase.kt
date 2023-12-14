package com.hisham.baseto.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hisham.baseto.data.models.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class BasetoDatabase : RoomDatabase() {
    abstract val dao: BasetoDao

    companion object {
        private lateinit var INSTANCE: BasetoDatabase
        fun initDatabase(context: Context): BasetoDatabase {
            synchronized(BasetoDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BasetoDatabase::class.java,
                        "Baseto_DB"
                    ).build();
                }
            }
            return INSTANCE

        }
    }
}
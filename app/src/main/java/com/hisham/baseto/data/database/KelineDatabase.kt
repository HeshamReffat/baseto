package com.hisham.baseto.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hisham.baseto.data.models.user.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class KelineDatabase : RoomDatabase() {
    abstract val dao: KelineDao

    companion object {
        private lateinit var INSTANCE: KelineDatabase
        fun initDatabase(context: Context): KelineDatabase {
            synchronized(KelineDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        KelineDatabase::class.java,
                        "kelineDb"
                    ).build();
                }
            }
            return INSTANCE

        }
    }
}
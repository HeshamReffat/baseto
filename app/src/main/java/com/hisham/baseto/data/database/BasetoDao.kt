package com.hisham.baseto.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hisham.baseto.data.models.user.User


@Dao
interface BasetoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("Select * From user Where id=:id ")
    fun getUserById(id: Int): User
}
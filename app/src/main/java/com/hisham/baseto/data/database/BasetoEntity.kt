package com.hisham.baseto.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KelineEntity")
data class BasetoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val phone:String,
    val email:String,
)
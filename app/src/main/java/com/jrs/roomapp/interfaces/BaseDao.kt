package com.jrs.roomapp.interfaces

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(vararg obj:T)

    @Delete
    suspend fun delete(vararg obj:T)
}
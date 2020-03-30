package com.jrs.roomapp.database.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jrs.roomapp.database.user.User
import com.jrs.roomapp.interfaces.BaseDao

@Dao
abstract class UserDao : BaseDao<User> {

    @Query("SELECT id, name, last_name, phone from Users ORDER BY name ASC")
    abstract fun getAll(): LiveData<List<User>>

    @Query("SELECT id, name, last_name, phone FROM Users WHERE :column LIKE :value")
    abstract fun getUserByData(column:String, value: String): User

    @Query("DELETE FROM Users")
    abstract suspend fun deleteAll()

}
package com.jrs.roomapp.database.user

import androidx.lifecycle.LiveData
import com.jrs.roomapp.database.user.User
import com.jrs.roomapp.database.user.UserDao

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAll()

    suspend fun insertOrUpdateUser(user: User) = userDao.insertOrUpdate(user)

    suspend fun deleteUser(user: User) = userDao.delete(user)

    fun getUser(id:Int): User = userDao.getUserByData("id",id.toString())

}
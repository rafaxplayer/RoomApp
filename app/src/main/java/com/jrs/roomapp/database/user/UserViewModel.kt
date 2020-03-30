package com.jrs.roomapp.database.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jrs.roomapp.database.RoomDataBase
import kotlinx.coroutines.launch


class UserViewModel (application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: UserRepository
    // LiveData gives us updated words when they change.
    val allUsers: LiveData<List<User>>

    init {
        val userDao = RoomDataBase.getDatabase(
            application,
            viewModelScope
        ).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers

    }

    fun insertOrUpdateUser(user: User) = viewModelScope.launch{
        repository.insertOrUpdateUser(user)
    }

    fun deleteUser(user: User)= viewModelScope.launch{
        repository.deleteUser(user)
    }

    fun getUserById(id:Int): User = repository.getUser(id)


}
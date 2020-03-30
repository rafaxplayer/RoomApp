package com.jrs.roomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jrs.roomapp.database.user.User
import com.jrs.roomapp.database.user.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class RoomDataBase:RoomDatabase() {

    abstract fun userDao(): UserDao

    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var userDao = database.userDao()

                    // Delete all content here.
                    userDao.deleteAll()

                    // Add sample words.
                    var user = User(
                        null,
                        name = "Antonio",
                        lastname = "sanchez",
                        phone = "63259854785"
                    )
                    userDao.insertOrUpdate(user)

                    user = User(
                        null,
                        name = "Maria",
                        lastname = "perez",
                        phone = "656984123"
                    )
                    userDao.insertOrUpdate(user)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDataBase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): RoomDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDataBase::class.java,
                    "users_database"
                ).addCallback(UserDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
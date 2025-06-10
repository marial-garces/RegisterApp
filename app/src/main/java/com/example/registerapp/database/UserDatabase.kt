package com.example.registerapp.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities =[User::class],
    version = 1,
    exportSchema = true,
//    autoMigrations = [
//        AutoMigration(from = 1, to = 2)
//    ]
)

abstract class UserDatabase: RoomDatabase() {
    abstract val userDao: UserDatabaseDao

//    companion object{
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//
//        fun getInstance(context: Context): UserDatabase? {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if ( instance == null ){
//                    instance == Room.databaseBuilder(
//                        context.applicationContext,
//                        UserDatabase::class.java,
//                        "user_detail_database"
//                    )
//                        .fallbackToDestructiveMigration(false)
//                        .build()
//
//                    INSTANCE = instance
//                }
//                return instance
//            }
//        }
//    }
}
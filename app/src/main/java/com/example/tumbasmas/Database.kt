package com.example.tumbasmas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if(INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE =
                        Room.databaseBuilder( context.applicationContext,
                            AppDatabase::class.java,"UserAPPDB"
                        ).build()
                }
            }
            return INSTANCE
        }
        fun destroyDatabase(){
            INSTANCE = null
        }
    }
}
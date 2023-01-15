package com.example.projectone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.model.Selic
import com.example.projectone.utils.Constants.Companion.DATABASE_NAME

@Database(entities = arrayOf(Selic::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun SelicDao() : SelicDao

    companion object {

        @Volatile
        private var INTANCE: AppDatabase? = null

    fun getAppDatabase(context: Context): AppDatabase {

        if (INTANCE != null) return INTANCE!!

        synchronized(this){
            INTANCE = Room
                .databaseBuilder(context, AppDatabase::class.java,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

            return INTANCE!!
        }
    }
}
}
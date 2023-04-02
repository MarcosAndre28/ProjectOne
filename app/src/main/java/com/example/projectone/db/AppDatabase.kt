package com.example.projectone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projectone.db.converter.StringListConverter
import com.example.projectone.db.dao.CryptoDao
import com.example.projectone.db.dao.InflationDao
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.dao.TickerDao
import com.example.projectone.db.model.*
import com.example.projectone.utils.Constants.Companion.DATABASE_NAME

@Database(entities = arrayOf(SelicModelDB::class, InflationModelDB::class, TickerModelDB::class, CryptoModelDB::class, CryptoAvailableModelDB::class), version = 6, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun SelicDao() : SelicDao
    abstract fun InflationDao() : InflationDao
    abstract fun TickerDao() : TickerDao
    abstract fun CryptoDao() : CryptoDao

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
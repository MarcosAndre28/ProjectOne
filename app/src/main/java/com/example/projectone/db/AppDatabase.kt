package com.example.projectone.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.projectone.db.converter.StringListConverter
import com.example.projectone.db.dao.*
import com.example.projectone.db.model.*
import com.example.projectone.utils.Constants.Companion.DATABASE_NAME

@Database(entities = arrayOf(SelicModelDB::class, InflationModelDB::class, TickerModelDB::class, CryptoModelDB::class, CryptoAvailableModelDB::class, FavoriteTickerModelDB::class), version = 7, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun SelicDao() : SelicDao
    abstract fun InflationDao() : InflationDao
    abstract fun TickerDao() : TickerDao
    abstract fun CryptoDao() : CryptoDao
    abstract fun FavoriteTickerDao() : FavoriteTickerDao

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
package com.example.projectone.db.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.CRYPTO_AVAILABLE)
@Parcelize
data class CryptoAvailableModelDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "coins")
    val coins: String
): Parcelable
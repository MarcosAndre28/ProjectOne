package com.example.projectone.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants.Companion.SELIC

@Entity(tableName = SELIC)
data class Selic(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Long,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "value")
    val value: String,

    @ColumnInfo(name = "epoch_date")
    val epochDate: Long
)

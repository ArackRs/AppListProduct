package dev.arack.applistproduct.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Product (

    @PrimaryKey(autoGenerate = true)
    val id : Int?,

    @ColumnInfo
    val title : String,

    @ColumnInfo
    val price : Double,

    @ColumnInfo
    val category : String,

    @ColumnInfo
    val description : String,

    @ColumnInfo
    val image : String
)
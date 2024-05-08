package dev.arack.applistproduct.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.arack.applistproduct.model.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun getProductDao() : ProductDao

    companion object {
        // Creamos una INSTANCIA nula
        private var INSTANCE : ProductDatabase? = null
        // Creamos fun para obtener la base de datos unica
        fun getInstance(context: Context) : ProductDatabase{
            if (INSTANCE == null){
                INSTANCE = Room
                    .databaseBuilder(context, ProductDatabase::class.java, "productsv1.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as ProductDatabase
        }
    }
}
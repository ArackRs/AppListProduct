package dev.arack.applistproduct.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.arack.applistproduct.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAllProducts() : List<Product>

    @Insert
    fun insertProduct(vararg product : Product)

    @Delete
    fun deleteProduct(vararg product : Product)

    @Update
    fun updateProduct(vararg product : Product)
}
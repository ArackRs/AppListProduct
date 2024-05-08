package dev.arack.applistproduct.network

import dev.arack.applistproduct.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    fun getAllProducts() : Call<List<Product>>
}
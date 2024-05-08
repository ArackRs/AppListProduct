package dev.arack.applistproduct.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.arack.applistproduct.R
import dev.arack.applistproduct.adapter.OnItemClickListener
import dev.arack.applistproduct.adapter.ProductAdapter
import dev.arack.applistproduct.db.ProductDatabase
import dev.arack.applistproduct.model.Product
import dev.arack.applistproduct.network.ProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FindActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var product : Product
    lateinit var products : List<Product>
    lateinit var productAdapter : ProductAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_find)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadProducts()
    }

    private fun loadProducts() {
        var rvProducts = findViewById<RecyclerView>(R.id.rvProducts)


        var retrofit = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ProductService::class.java)

        var request = service.getAllProducts()

        request.enqueue(object : Callback<List<Product>>{
            override fun onResponse(p0: Call<List<Product>>, p1: Response<List<Product>>) {
                products = p1.body()!!
                productAdapter = ProductAdapter(products, this@FindActivity)
                rvProducts.adapter = productAdapter
                rvProducts.layoutManager = LinearLayoutManager(this@FindActivity)
            }

            override fun onFailure(p0: Call<List<Product>>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onItemClicked(product: Product) {
        ProductDatabase.getInstance(this).getProductDao().insertProduct(product)
        finish()

        Toast.makeText(this, "Person "+product.title+" added to favorites", Toast.LENGTH_SHORT).show()
    }
}
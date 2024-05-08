package dev.arack.applistproduct.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import dev.arack.applistproduct.R
import dev.arack.applistproduct.adapter.OnItemClickListener
import dev.arack.applistproduct.adapter.ProductAdapter
import dev.arack.applistproduct.db.ProductDatabase
import dev.arack.applistproduct.model.Product

class FavouriteActivity : AppCompatActivity(), OnItemClickListener {

    lateinit var products : List<Product>
    lateinit var productFavoritesAdapter : ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favourite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()

        loadFavorites()

        var rvFavorites = findViewById<RecyclerView>(R.id.rvFavourites)
        productFavoritesAdapter = ProductAdapter(products, this)
        rvFavorites.adapter = productFavoritesAdapter
        rvFavorites.layoutManager = LinearLayoutManager(this)
    }

    private fun loadFavorites() {
        products = ProductDatabase.getInstance(this).getProductDao().getAllProducts()
    }

    override fun onItemClicked(product: Product) {
        var intent = Intent(this, MainActivity::class.java)
        var gson = Gson()

        intent.putExtra("productDeleted", gson.toJson(product))
        startActivity(intent)
    }
}
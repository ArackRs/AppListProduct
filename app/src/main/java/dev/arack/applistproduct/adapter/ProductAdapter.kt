package dev.arack.applistproduct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import dev.arack.applistproduct.R
import dev.arack.applistproduct.model.Product
import dev.arack.applistproduct.ui.FindActivity

class ProductAdapter(val products : List<Product>, val itemClickListener: OnItemClickListener) : Adapter<PrototypeProduct>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrototypeProduct {
        var view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_product, parent, false)

        return PrototypeProduct(view)
    }

    override fun onBindViewHolder(holder: PrototypeProduct, position: Int) {
        holder.bind(products.get(position), itemClickListener)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}

class PrototypeProduct(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvProdTitle = itemView.findViewById<TextView>(R.id.tvProdTitle)
    var ivProd = itemView.findViewById<ImageView>(R.id.ivProd)
    var ibFavourite = itemView.findViewById<ImageButton>(R.id.ibFavourite)

    fun bind(product : Product, itemClickListener: OnItemClickListener){
        tvProdTitle.text = product.title

        Glide.with(itemView).load(product.image).into(ivProd)

        ibFavourite.setOnClickListener {
            itemClickListener.onItemClicked(product)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(product : Product)
}
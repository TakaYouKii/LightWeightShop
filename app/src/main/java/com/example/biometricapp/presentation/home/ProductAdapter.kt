package com.example.biometricapp.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.biometricapp.R
import com.example.biometricapp.databinding.ProductItemsBinding
import com.example.biometricapp.model.Product

class ProductAdapter(val addProductToCart:(product: Product)->Unit): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products = arrayListOf<Product>()

    fun addProductList(productList: List<Product>){
        products.clear()
        products.addAll(productList)
        notifyDataSetChanged()
    }


    inner class ProductViewHolder(private val binding: ProductItemsBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product){
            with(binding){
                tvName.text = product.name
                tvPrice.text = product.price.toString()
                btnAddToCart.setOnClickListener {
                    addProductToCart(product)
                }


                Glide.with(binding.root.context) // 'this' указывает на текущий фрагмент
                    .load(product.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_cart)
                    .error(R.drawable.ic_home)
                    .into(imgProduct)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }


}
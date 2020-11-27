package com.jaxfire.depop.ui.fragments.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.jaxfire.depop.R
import com.jaxfire.depop.data.repository.entity.Product
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(private val products: MutableList<Product>, private val itemClickListener: ProductItemClickListener) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    interface ProductItemClickListener {
        fun productSelectionHandler(product: Product)
    }

    fun setProducts(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductHolder =
        ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_list_item, parent, false),
            itemClickListener
        )


    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.bindProduct(products[position])
    }

    override fun getItemCount() = products.size

    class ProductHolder(private val view: View, private val itemClickListener: ProductItemClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var product: Product

        init {
            view.setOnClickListener(this)
            view.isEnabled = false
        }

        override fun onClick(v: View) {
            itemClickListener.productSelectionHandler(product)
        }

        fun bindProduct(product: Product) {
            this.product = product
            view.listItemUserId.text = product.userId.toString()
            view.listItemShortDescription.text = product.description
            view.isEnabled = true
        }
    }
}

package com.jaxfire.depop.ui.fragments.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaxfire.depop.R
import com.jaxfire.depop.data.repository.entity.Product
import com.jaxfire.depop.data.repository.entity.Product.Picture.PictureSize
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductAdapter(
    private val products: MutableList<Product>,
    private val itemClickListener: ProductItemClickListener
) :
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

    class ProductHolder(
        private val view: View, private val itemClickListener: ProductItemClickListener
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var product: Product

        init {
            view.setOnClickListener(this)
            view.isClickable = false
        }

        override fun onClick(v: View) {
            itemClickListener.productSelectionHandler(product)
        }

        fun bindProduct(product: Product) {
            this.product = product
            view.textViewProductListItemTitle.text = product.userId.toString()
            view.textViewProductListItemShortDescription.text = product.description
            Glide
                .with(view)
                .load(product.pictures.firstOrNull()?.getImageUrl(PictureSize.S))
                .into(view.imageViewProductListItemImage)
            view.isClickable = true
        }
    }
}

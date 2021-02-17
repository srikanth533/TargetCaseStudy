package com.target.targetcasestudy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.repository.remote.model.Product
import com.target.targetcasestudy.ui.activity.MainActivity

class DealItemAdapter(private val activity: FragmentActivity?) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    private var productList: List<Product> = emptyList()

    fun setData(productList: List<Product>) {
        this.productList = productList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder =
        DealItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.deal_list_item, parent, false)
        )

    override fun getItemCount(): Int = if (productList.isNullOrEmpty()) 0 else productList.size

    override fun onBindViewHolder(holder: DealItemViewHolder, position: Int) {
        val product = productList[position]

        product.apply {
            this.imageUrl?.let {
                Picasso.get().load(it).into(holder.productImage)
            }
            holder.productTitle.text = this.title
            holder.price.text = this.regularPrice.price
            holder.aisle.text = this.aisle
        }

        holder.productLayout.setOnClickListener {
            if (activity != null && activity is MainActivity) {
                activity.onItemClicked(product)
            }
        }
    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val productLayout = itemView.findViewById<ConstraintLayout>(R.id.product_layout)
    val productImage = itemView.findViewById<ImageView>(R.id.product_image)
    val productTitle = itemView.findViewById<TextView>(R.id.product_title)
    val price = itemView.findViewById<TextView>(R.id.price)
    val aisle = itemView.findViewById<TextView>(R.id.aisle_info)
}
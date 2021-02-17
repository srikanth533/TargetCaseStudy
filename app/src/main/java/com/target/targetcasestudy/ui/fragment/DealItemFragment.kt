package com.target.targetcasestudy.ui.fragment

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

import com.target.targetcasestudy.R
import com.target.targetcasestudy.repository.remote.model.Product
import com.target.targetcasestudy.ui.Constants


class DealItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_deal_item, container, false)

        val productImage = view.findViewById<ImageView>(R.id.product_image)
        val salePrice = view.findViewById<TextView>(R.id.sale_price)
        val regularPrice = view.findViewById<TextView>(R.id.regular_price)
        val productTitle = view.findViewById<TextView>(R.id.product_title)
        val productDescription = view.findViewById<TextView>(R.id.product_description)

        val bundle = arguments
        if (bundle != null && bundle.containsKey(Constants.PRODUCT_KEY)) {
            val product: Product? = bundle.getParcelable(Constants.PRODUCT_KEY)
            product?.let {
                productTitle.text = it.title
                productDescription.text = it.description
                it.imageUrl?.let { url ->
                    Picasso.get().load(url).into(productImage)
                }

                regularPrice.text = String.format("Reg: %s", it.regularPrice.price)
                it.salePrice?.let { s ->
                    salePrice.text = s.price
                    regularPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }
        }

        return view
    }
}

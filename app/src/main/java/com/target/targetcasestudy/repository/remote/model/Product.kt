package com.target.targetcasestudy.repository.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product (
    val id: Int,
    val title: String,
    val aisle: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("regular_price")
    val regularPrice: RegularPrice,
    @SerializedName("sale_price")
    val salePrice: SalePrice?
): Parcelable
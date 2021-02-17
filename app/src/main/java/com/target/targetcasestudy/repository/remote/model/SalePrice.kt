package com.target.targetcasestudy.repository.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalePrice (
    @SerializedName("amount_in_cents")
    val amountInCents: Long,
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("display_string")
    val price: String
): Parcelable
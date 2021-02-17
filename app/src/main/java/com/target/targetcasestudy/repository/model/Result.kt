package com.target.targetcasestudy.repository.model

import com.target.targetcasestudy.repository.remote.model.Product
import java.lang.Exception

/**
 * Sealed class to handle Success and failure results
 */
sealed class Result {

    data class Success(val productList: List<Product>?): Result()
    data class Error(val exception: TargetException): Result()
}

data class TargetException(
    val errorMessage: String?
) : Exception()
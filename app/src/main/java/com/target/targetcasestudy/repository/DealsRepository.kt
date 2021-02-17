package com.target.targetcasestudy.repository

import com.target.targetcasestudy.repository.model.Result

interface DealsRepository {

    suspend fun fetchDeals(): Result
}
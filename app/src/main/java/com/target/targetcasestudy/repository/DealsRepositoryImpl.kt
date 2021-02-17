package com.target.targetcasestudy.repository

import com.target.targetcasestudy.repository.model.Result
import com.target.targetcasestudy.repository.model.TargetException
import com.target.targetcasestudy.repository.remote.DealsRemoteSource

class DealsRepositoryImpl(private val dealsRemoteSource: DealsRemoteSource): DealsRepository {

    override suspend fun fetchDeals(): Result {
        val response = dealsRemoteSource.fetchDeals()
        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()?.products)
        } else {
            Result.Error(TargetException(response.errorBody()?.toString()))
        }
    }
}
package com.target.targetcasestudy.repository.remote

import com.target.targetcasestudy.repository.remote.model.DealsResponse
import retrofit2.Response

interface DealsRemoteSource {

    suspend fun fetchDeals(): Response<DealsResponse>
}
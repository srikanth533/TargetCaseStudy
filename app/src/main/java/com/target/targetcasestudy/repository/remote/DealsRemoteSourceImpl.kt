package com.target.targetcasestudy.repository.remote

import com.target.targetcasestudy.repository.remote.api.TargetService
import com.target.targetcasestudy.repository.remote.model.DealsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DealsRemoteSourceImpl(private val targetService: TargetService): DealsRemoteSource {

    companion object {
        fun create(baseURL: String): DealsRemoteSource {
            // Initialization of Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(TargetService::class.java)
            return DealsRemoteSourceImpl(service)
        }
    }

    override suspend fun fetchDeals(): Response<DealsResponse> =
        targetService.fetchDeals()
}
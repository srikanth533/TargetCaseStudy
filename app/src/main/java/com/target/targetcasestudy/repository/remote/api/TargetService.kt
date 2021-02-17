package com.target.targetcasestudy.repository.remote.api

import com.target.targetcasestudy.repository.remote.model.DealsResponse
import retrofit2.Response
import retrofit2.http.GET

interface TargetService {

    @GET("mobile_case_study_deals/v1/deals")
    suspend fun fetchDeals(): Response<DealsResponse>
}
package com.target.targetcasestudy.dagger

import com.target.targetcasestudy.repository.Constants
import com.target.targetcasestudy.repository.DealsRepository
import com.target.targetcasestudy.repository.DealsRepositoryImpl
import com.target.targetcasestudy.repository.remote.DealsRemoteSource
import com.target.targetcasestudy.repository.remote.DealsRemoteSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TargetModule {

    @Provides
    @Singleton
    fun provideDealsRemoteDataSource(): DealsRemoteSource =
        DealsRemoteSourceImpl.create(Constants.BASE_URL)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: DealsRemoteSource): DealsRepository =
        DealsRepositoryImpl(remoteDataSource)
}
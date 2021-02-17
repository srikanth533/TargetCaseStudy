package com.target.targetcasestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.target.targetcasestudy.repository.DealsRepository
import com.target.targetcasestudy.repository.model.Result

class DealsViewModel(val repository: DealsRepository) : ViewModel() {

    fun fetchDeals(): LiveData<Result> = liveData {
        val data = repository.fetchDeals()
        emit(data)
    }
}

class DealsViewModelFactory(private val repository: DealsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DealsViewModel::class.java)) {
            return DealsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

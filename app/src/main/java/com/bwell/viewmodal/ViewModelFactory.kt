package com.bwell.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bwell.network.ApiService
import com.bwell.repository.BookingRepository


class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingViewModel::class.java)) {
            return BookingViewModel(BookingRepository(apiService)) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }


}
package com.bwell.viewmodal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bwell.helper.ResultState
import com.bwell.modal.Booking
import com.bwell.repository.BookingRepository
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository) : ViewModel() {

    fun getBooking(): MutableLiveData<ResultState<Booking>> {

        val bookingResponse = MutableLiveData<ResultState<Booking>>()

        viewModelScope.launch {

            val response = repository.getBooking()

            response.let {
                when (it) {
                    is ResultState.Success -> {
                        val user = it.extractData
                        user?.let { list ->
                            bookingResponse.postValue(ResultState.Success(list))
                        }
                    }
                    else -> bookingResponse.postValue(it)
                }
            }

        }
        return bookingResponse
    }
}
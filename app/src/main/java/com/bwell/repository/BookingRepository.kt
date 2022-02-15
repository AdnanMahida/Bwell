package com.bwell.repository

import com.bwell.helper.ResultState
import com.bwell.modal.Booking
import com.bwell.network.ApiService
import retrofit2.HttpException

class BookingRepository constructor(private val apiService: ApiService) : BaseRepository() {
    companion object {
        private val TAG = BookingRepository::class.java.name

    }

    suspend fun getBooking(): ResultState<Booking> {
        try {
            val response = apiService.getBooking()
            response.let {
                it?.body()?.let { res ->
                    return handleSuccess(res)
                }
                it?.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            return handleException(errorCode)
                        }
                    }
                }
            }
            return handleException(response?.code() ?: GENERAL_ERROR_CODE)

        } catch (error: HttpException) {
            return handleException(error.code())
        }
    }
}
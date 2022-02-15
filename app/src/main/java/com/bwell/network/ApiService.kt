package com.bwell.network

import com.bwell.modal.Booking
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/?page=3&results=10")
    suspend fun getBooking(
    ): Response<Booking>?
}
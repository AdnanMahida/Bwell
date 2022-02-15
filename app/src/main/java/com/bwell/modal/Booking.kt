package com.bwell.modal

import com.google.gson.annotations.SerializedName


data class Booking(
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("info") var info: Info? = Info()
)
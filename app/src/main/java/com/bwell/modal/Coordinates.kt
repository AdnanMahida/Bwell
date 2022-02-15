package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Coordinates(
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("longitude") var longitude: String? = null
):Serializable
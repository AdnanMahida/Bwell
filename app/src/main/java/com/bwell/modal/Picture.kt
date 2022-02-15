package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Picture(
    @SerializedName("large") var large: String? = null,
    @SerializedName("medium") var medium: String? = null,
    @SerializedName("thumbnail") var thumbnail: String? = null
): Serializable
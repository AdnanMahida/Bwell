package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Name(
    @SerializedName("title") var title: String? = null,
    @SerializedName("first") var first: String? = null,
    @SerializedName("last") var last: String? = null
) : Serializable
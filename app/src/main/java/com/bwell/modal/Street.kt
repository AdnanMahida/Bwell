package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Street(

    @SerializedName("number") var number: String? = null,
    @SerializedName("name") var name: String? = null

) : Serializable
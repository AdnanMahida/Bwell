package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Timezone(
    @SerializedName("offset") var offset: String? = null,
    @SerializedName("description") var description: String? = null
) : Serializable
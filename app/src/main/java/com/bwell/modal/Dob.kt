package com.bwell.modal

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dob (
  @SerializedName("date" ) var date : String? = null,
  @SerializedName("age"  ) var age  : Int?    = null
): Serializable
package com.bwell.helper

import android.annotation.SuppressLint
import android.telephony.PhoneNumberUtils
import java.text.ParseException
import java.text.SimpleDateFormat


object Helper {
    @SuppressLint("SimpleDateFormat")
    fun getDateFromDateTimeStamp(date: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val formatter = SimpleDateFormat("dd-MM-yyyy")
        var formattedDate: String? = null
        try {
            formattedDate = formatter.format(parser.parse(date))
        } catch (e: ParseException) {
            formattedDate = date
            e.printStackTrace()
        }
        return formattedDate.toString()
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeFromDateTimeStamp(date: String?): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val formatter = SimpleDateFormat("HH:mm:ss")
        var formattedDate: String? = null
        try {
            formattedDate = formatter.format(parser.parse(date))
        } catch (e: ParseException) {
            formattedDate = date
            e.printStackTrace()
        }
        return formattedDate.toString()
    }

    fun contactNoFormat(unformattedNumber: String): String {
        return PhoneNumberUtils.formatNumber(unformattedNumber)
    }
}
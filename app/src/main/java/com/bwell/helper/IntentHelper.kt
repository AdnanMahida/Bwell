package com.bwell.helper

import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentHelper {
    fun sendMail(context: Context, email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        context.startActivity(Intent.createChooser(intent, "Send mail"))
    }

    fun makeCall(context: Context, contact: String) {
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$contact")
        context.startActivity(callIntent)
    }

}
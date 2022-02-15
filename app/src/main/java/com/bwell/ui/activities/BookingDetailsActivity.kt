package com.bwell.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bwell.databinding.ActivityBookingDetailsBinding
import com.bwell.helper.Helper.contactNoFormat
import com.bwell.helper.IntentHelper
import com.bwell.helper.loadImage
import com.bwell.modal.Results
import com.bwell.util.Const

class BookingDetailsActivity : AppCompatActivity() {
    private var results: Results? = null
    private lateinit var binding: ActivityBookingDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getData()
        binding.setUpEvents()
    }

    private fun ActivityBookingDetailsBinding.getData() {
        results = intent.getSerializableExtra(Const.EXTRA_RESULTS) as Results

        toolbarTitle.text = "${results?.name?.title} ${results?.name?.first} ${results?.name?.last}"
        loadImage(imgFProfile, results?.picture?.large)
        txtContact.text = contactNoFormat(results?.cell.toString())
        txtEmail.text = results?.email
    }

    private fun ActivityBookingDetailsBinding.setUpEvents() {
        toolbar.setNavigationOnClickListener { onBackPressed() }

        txtContact.setOnClickListener(::makeCall)
        btnContact.setOnClickListener(::makeCall)
        txtEmail.setOnClickListener(::sendMail)
        btnBook.setOnClickListener(::sendMail)

    }

    private fun makeCall(view: View) {
        IntentHelper.makeCall(this@BookingDetailsActivity, results?.cell.toString())
    }

    private fun sendMail(view: View) {
        IntentHelper.sendMail(this@BookingDetailsActivity, results?.email.toString())
    }
}
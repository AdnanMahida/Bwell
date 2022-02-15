package com.bwell.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bwell.R
import com.bwell.databinding.ActivityMainBinding
import com.bwell.helper.ResultState
import com.bwell.modal.Results
import com.bwell.network.ApiService
import com.bwell.network.RetrofitService
import com.bwell.ui.adapter.BookingAdapter
import com.bwell.util.Const
import com.bwell.util.inVisibleShimmer
import com.bwell.util.visibleShimmer
import com.bwell.viewmodal.BookingViewModel
import com.bwell.viewmodal.ViewModelFactory


class MainActivity : AppCompatActivity() {

    private var bookingViewModel: BookingViewModel? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModels()
        binding.setUpList()
        binding.setUpEvents()
    }

    private fun ActivityMainBinding.setUpList() {
        val adapter =
            BookingAdapter(object : BookingAdapter.OnBookingClickListener {
                override fun onAddClick(res: Results) {
                    val intent = Intent(this@MainActivity, BookingDetailsActivity::class.java)
                    intent.putExtra(Const.EXTRA_RESULTS, res)
                    startActivity(intent)
                }
            })
        mainRecycle.layoutManager = LinearLayoutManager(this@MainActivity)
        mainRecycle.adapter = adapter
        mainRecycle.addItemDecoration(
            DividerItemDecoration(
                mainRecycle.context,
                DividerItemDecoration.VERTICAL
            )
        )

        visibleShimmer()
        bookingViewModel?.getBooking()?.observe(this@MainActivity, {
            when (it) {
                is ResultState.Error -> {
                    Toast.makeText(this@MainActivity, "Error...", Toast.LENGTH_LONG).show()
                    visibleShimmer()
                }

                is ResultState.InProgress -> {
                    visibleShimmer()
                }

                is ResultState.Success -> {
                    inVisibleShimmer()
                    adapter.submitList(it.data.results)
                }
            }
        })


    }

    private fun ActivityMainBinding.setUpEvents() {
        bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_more -> {
                    Toast.makeText(this@MainActivity, "More...", Toast.LENGTH_LONG).show()
                    true
                }
                else -> {
                    false

                }
            }
        }
        bottomAppBar.setNavigationOnClickListener {
            Toast.makeText(this@MainActivity, "Calendar...", Toast.LENGTH_LONG).show()
        }
    }

    private fun initViewModels() {
        if (bookingViewModel == null) {
            bookingViewModel = ViewModelProvider(
                this@MainActivity,
                ViewModelFactory(RetrofitService.createService(ApiService::class.java))
            )[BookingViewModel::class.java]
        }
    }
}



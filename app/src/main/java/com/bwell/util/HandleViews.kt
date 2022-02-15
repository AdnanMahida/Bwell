package com.bwell.util

import android.view.View
import com.bwell.databinding.ActivityMainBinding

//extension of view function
fun ActivityMainBinding.visibleShimmer() {
    mainShimmer.mainShimmer.visibility = View.VISIBLE
    mainShimmer.mainShimmer.startShimmer()
    mainRecycle.visibility = View.GONE
}

fun ActivityMainBinding.inVisibleShimmer() {
    mainShimmer.mainShimmer.stopShimmer()
    mainShimmer.mainShimmer.visibility = View.GONE
    mainRecycle.visibility = View.VISIBLE
}

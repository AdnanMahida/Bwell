package com.bwell.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bwell.databinding.RecycleListBinding
import com.bwell.helper.Helper
import com.bwell.helper.loadImage
import com.bwell.modal.Results


class BookingAdapter(
    private val listener: OnBookingClickListener
) : ListAdapter<Results, BookingAdapter.BookingViewHolder>(Diff) {

    class BookingViewHolder(val binding: RecycleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(booking: Results) {
            binding.apply {
                txtName.text = "${booking.name?.title} ${booking.name?.first} ${booking.name?.last}"
                txtDOB.text = Helper.getDateFromDateTimeStamp(booking.dob?.date)
                txtAddress.text =
                    "${booking.location?.street?.name}-${booking.location?.street?.number},${booking.location?.city},${booking.location?.state},${booking.location?.country}"
                txtSkill.text = booking.gender
                txtTime.text = Helper.getTimeFromDateTimeStamp(booking.dob?.date)
                txtPrice.text = booking.location?.country ?: ""
                loadImage(imgProfile, booking.picture?.medium)
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(
            oldItem: Results,
            newItem: Results
        ): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            RecycleListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it)
            holder.binding.root.setOnClickListener {
                listener.onAddClick(currentList[position])
            }
            holder.binding.btnDetails.setOnClickListener {
                listener.onAddClick(currentList[position])
            }
        }
    }

    interface OnBookingClickListener {
        fun onAddClick(booking: Results)
    }
}

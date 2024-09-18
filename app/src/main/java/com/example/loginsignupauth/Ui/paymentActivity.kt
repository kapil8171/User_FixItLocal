package com.example.loginsignupauth.Ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.icu.text.DateFormatSymbols
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupauth.DataClasses.BookingRequest
import com.example.loginsignupauth.DataClasses.BookingStatus
import com.example.loginsignupauth.R
import com.example.loginsignupauth.databinding.ActivityPaymentBinding
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding

    private lateinit var tvDatePicker : TextView
    private lateinit var btnDatePicker: ImageView

    private lateinit var tvTime : TextView
    private lateinit var btnTimePicker: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the name from the intent extras
        val name = intent.getStringExtra("providerName")
        val mob = intent.getStringExtra("mobile_num")

        // Set the name to the TextView
         binding.servicePersonName.text = name
         binding.servicePersonMobno.text = mob




        // Delay in milliseconds after which the dialog should be dismissed
        val delayMillis = 3000 // 3 seconds

        // Create a Handler to delay the dismissal of the dialog
        val handler = android.os.Handler()

        // Post a delayed action to dismiss the dialog after the specified delay
        handler.postDelayed({
            // Dismiss the dialog after the delay
            dismissDialog()
        }, delayMillis.toLong())

         // implementing booking request
        // Handle confirm booking button click
        binding.confirmButtonbooking.setOnClickListener {
            // Create a unique ID for the booking request
            // Generate a unique ID for the new booking request
            val requestId =
                FirebaseDatabase.getInstance().reference.child("BookingRequests").push().key

// Check if requestId is not null before proceeding
            if (requestId != null) {
                // Get booking details
                val bookingDate = binding.tvDate.text.toString()
                val bookingTime =
                    binding.tvTime.text.toString() // Assuming you have a TextView for time
                val bookingLocation =
                    binding.locationTextView.text.toString() // Get location from user input
                val userName =
                    binding.userName.text.toString() // Get user's name from authentication or input

                // Check if all required fields are not empty
                if (bookingDate.isNotEmpty() && bookingTime.isNotEmpty() && bookingLocation.isNotEmpty() && userName.isNotEmpty()) {
                    // Store booking details in Firebase Realtime Database under "BookingRequests" node
                    FirebaseDatabase.getInstance().reference.child("BookingRequests")
                        .child(requestId)
                        .setValue(
                            BookingRequest(
                                requestId,
                                userName,
                                bookingDate,
                                bookingTime,
                                bookingLocation,
                                BookingStatus.PENDING // Set initial status as pending
                            )
                        )
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
//                                Toast.makeText(
//                                    this,
//                                    "Booking is successfully done",
//                                    Toast.LENGTH_SHORT
//                                ).show()
                                // Show booking confirmation dialog when booking is confirmed
                                showBookingConfirmationDialog()
//                                startActivity(Intent(this,MainActivity::class.java))


                            } else {
                                Toast.makeText(
                                    this,
                                    "Failed to Upload Details: ${task.exception}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Failed to Generate Request ID", Toast.LENGTH_SHORT).show()
            }
        }



            tvTime = binding.tvTime
        btnTimePicker = binding.btnTimePicker

        btnTimePicker.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val startHour = currentTime.get(Calendar.HOUR)
            val startMinute = currentTime.get(Calendar.MINUTE)

            val is24HourFormat = false // Set to false to use 12-hour format

            TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    // Convert hour to 12-hour format and determine AM/PM
                    val displayHour = if (hourOfDay == 0) 12 else if (hourOfDay > 12) hourOfDay - 12 else hourOfDay
                    val amPm = if (hourOfDay < 12) "AM" else "PM"
                    tvTime.text = String.format(Locale.getDefault(), "%02d:%02d %s", displayHour, minute, amPm)
                },
                startHour,
                startMinute,
                is24HourFormat
            ).show()
        }


        tvDatePicker =  binding.tvDate
        btnDatePicker = binding.btnDatePicker

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            updateLabel(myCalendar)
        }
        btnDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(
                Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        btnDatePicker.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }


//        binding.confirmButton-booking.setOnClickListener {
//            val intent = Intent(this@PaymentActivity,DoneActivity::class.java)
//            startActivity(intent)
//        }

    }

    private fun dismissDialog() {
        // Reset the text of date and time TextViews to empty string

    }

    private fun showBookingConfirmationDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_booking_confirmation)
        dialog.show()

        // Delay in milliseconds after which the dialog should be dismissed
        val delayMillis = 2000 // 2 seconds

        // Create a Handler to delay the dismissal of the dialog
        val handler = android.os.Handler()

        // Post a delayed action to dismiss the dialog after the specified delay
        handler.postDelayed({
            // Dismiss the dialog after the delay
            dialog.dismiss()
            binding.tvDate.text = ""
            binding.tvTime.text = ""
        }, delayMillis.toLong())
    }


    private fun updateLabel(myCalendar: Calendar) {
        val dateFormatSymbols = DateFormatSymbols.getInstance(Locale.getDefault())
        val dayOfWeek = dateFormatSymbols.shortWeekdays[myCalendar.get(Calendar.DAY_OF_WEEK)]
        val formattedDate = SimpleDateFormat("dd-MM-yyyy", Locale.UK).format(myCalendar.time)
        tvDatePicker.text = "$dayOfWeek, $formattedDate"
    }

}
package com.example.loginsignupauth.Ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.loginsignupauth.databinding.ActivityDoneBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class DoneActivity : AppCompatActivity(), PaymentResultListener {
         private lateinit var binding: ActivityDoneBinding

    private lateinit var txtPaymentStatus: TextView
    private lateinit var editAmount: EditText
    private lateinit var btnPayNow: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoneBinding.inflate(layoutInflater)
        setContentView(binding.root)


        txtPaymentStatus = binding.paymentStatus
        editAmount = binding.editAmount
        btnPayNow = binding.btnPay

        btnPayNow.setOnClickListener {
            savePayments(editAmount.text.toString().trim().toInt())
        }

        Checkout.preload(this@DoneActivity)

    }

    private fun savePayments(amount: Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_rjBrY0Ch8CNSZd")
        try {
            val options = JSONObject()
            options.put("name", "Razorpay Demo")
            options.put("description", "If you like this, buy me a coffee")
            // options.put("image", "image url")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            // Calculate amount in paise (multiply by 100)
            val amountInPaise = amount * 100
            options.put("amount", amountInPaise.toString())

            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            checkout.open(this@DoneActivity, options)

        } catch (e: Exception) {
            Toast.makeText(
                this@DoneActivity,
                "Error in Payment: " + e.message,
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {

        txtPaymentStatus.text = p0
        txtPaymentStatus.setTextColor(Color.GREEN)
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        txtPaymentStatus.text = p1
        txtPaymentStatus.setTextColor(Color.RED)
    }

}
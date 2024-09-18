package com.example.loginsignupauth.Ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.loginsignupauth.Adapters.FragmentAdapter
import com.example.loginsignupauth.Fragments.AboutFragment
import com.example.loginsignupauth.Fragments.GalleryFragment
import com.example.loginsignupauth.Fragments.ReviewFragment
import com.example.loginsignupauth.databinding.ActivityBookingBinding
import com.google.android.material.tabs.TabLayout

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Retrieve the name from the intent extras
        val name = intent.getStringExtra("name")
        val loc = intent.getStringExtra("location")
        val service = intent.getStringExtra("service")
        val mobnumber = intent.getStringExtra("mobile_number")

        // Set the name to the TextView
        binding.providerName.text = name
        binding.providerName2.text = name
        binding.providerLocation.text = loc
        binding.ProviderService.text = service

        // Retrieve the image URI or URL from the intent extras
        val imageUrl = intent.getStringExtra("Image")

        // Load and display the image into the ImageView using Glide
        Glide.with(this)
            .load(imageUrl)
            .into(binding.ProviderImage2)

        Glide.with(this)
            .load(imageUrl)
            // .error(R.drawable.ac_serve) // Placeholder image to show on error
            .into(binding.ProviderImage)


        binding.booknow.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("providerName", binding.providerName.text.toString())
            intent.putExtra("mobile_num", mobnumber)
            startActivity(intent)
        }


        var viewPager: ViewPager = binding.viewPager
        var tabLayout: TabLayout = binding.tablayout

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(AboutFragment(), "About")
        fragmentAdapter.addFragment(GalleryFragment(), "Gallery")
        fragmentAdapter.addFragment(ReviewFragment(), "Review")


        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)


    }
}
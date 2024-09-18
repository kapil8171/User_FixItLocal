package com.example.loginsignupauth.Ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.loginsignupauth.databinding.ActivitySplash2Binding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplash2Binding
    private val splashtime: Long = 2000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplash2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        // Load the Lottie animation view
        val lottieAnimationView: LottieAnimationView = binding.lottieAnimationView
        lottieAnimationView.visibility = View.VISIBLE
        lottieAnimationView.playAnimation()


        // Using a Handler to delay the execution of the next activity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start your main activity here
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Close this activity
        }, splashtime)

//        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
//        val slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
//        val bounceAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce)
//        val Rotate_clockAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
//        val ScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)
//        val SlideInAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in)
//        val fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

//        binding.imageView3.visibility = View.VISIBLE
//        binding.imageView3.startAnimation(Rotate_clockAnimation)
//
//        binding.textView.visibility = View.VISIBLE
//        binding.textView.startAnimation(slideUpAnimation)
//
//        binding.textView2.visibility = View.VISIBLE
//        binding.textView2.startAnimation(slideUpAnimation)


    }
}
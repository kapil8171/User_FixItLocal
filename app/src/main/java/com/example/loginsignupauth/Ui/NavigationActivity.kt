package com.example.loginsignupauth.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.loginsignupauth.R

class NavigationActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer2) as NavHostFragment
        navController = navHostFragment.navController

        // Handle the intent to navigate to the desired fragment
        handleIntent()
    }

    private fun handleIntent() {
        val destinationId = intent.getIntExtra("destinationId", -1)
        if (destinationId != -1) {
            // Navigate to the desired fragment using NavController
            navController.navigate(destinationId)
        }
    }
}

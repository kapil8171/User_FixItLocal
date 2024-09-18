package com.example.loginsignupauth.Ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.loginsignupauth.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Assuming you have a NavHostFragment with id "mainContainer" in your activity_main.xml
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

        // Assuming you have a BottomNavigationView with id "bottomNavigationView" in your activity_main.xml
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)


        // Set up BottomNavigationView with the NavController
        bottomNavigationView.setupWithNavController(navController)




    }
}
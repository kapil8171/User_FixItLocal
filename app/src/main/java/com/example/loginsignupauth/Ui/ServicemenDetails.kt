package com.example.loginsignupauth.Ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginsignupauth.Adapters.MyAdapter
import com.example.loginsignupauth.DataClasses.Person
import com.example.loginsignupauth.R


class ServicemenDetails : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servicemen_details)

        var persons = arrayListOf<Person>(
//            Person("Rohan", "Meerut", "Cleaning", R.drawable.balajims, true),
//            Person("Dash", "Pakistan", "Plumbing", R.drawable.balajims,true),
//            Person("Rohan", "Meerut", "Cleaning", R.drawable.balajims,true),
//            Person("Dash", "Pakistan", "Plumbing", R.drawable.balajims,true),
//            Person("Rohan", "Meerut", "Cleaning", R.drawable.balajims,true),
//            Person("Dash", "Pakistan", "Plumbing", R.drawable.balajims,true),
//            // Add your new person with the callButton option

        )

        val service  = intent.getStringExtra("Service")
            if(service == "Cleaning" ){
                persons.add(Person("Aadarsh", "UNA", "Cleaner", R.drawable.balajims, true,"8360957069"))
                persons.add( Person("Abhinav", "UNA", "Cleaner", R.drawable.balajims,true,"98754684542"))

            }
            else if(service == "Painting"){
                persons.add(Person("Aditya Goyal", "UNA", "Painter", R.drawable.wall_painter2,true,"456974562"))
                persons.add(Person("Aditya Kumar", "UNA", "Painter", R.drawable.wall_painter2,true,"876247645"))
            }
            else if(service == "Electrician"){
                persons.add(Person("Aditya arya", "UNA", "Electrician", R.drawable.electrician1,true,"456974562"))
                persons.add(Person("Ajeet kumar", "UNA", "Electrician", R.drawable.electrician2,true,"876247645"))
            }
            else if(service == "Plumber"){
                persons.add(Person("Akshit mehta", "UNA", "Plumber", R.drawable.plumber1,true,"456974562"))
                persons.add(Person("Anish kumar", "UNA", "Plumber", R.drawable.plumber2,true,"876247645"))
            }
            else if(service == "Carpenter"){
                persons.add(Person("Anant aggarwal", "UNA", "Carpenter", R.drawable.carpenter1,true,"456974562"))
                persons.add(Person("Aryan kumar", "UNA", "Carpenter", R.drawable.carpenter2,true,"876247645"))
            }
            else if(service == "Home Builder"){
                persons.add(Person("Gurman", "UNA", "Home Builder", R.drawable.homebuilder1,true,"456974562"))
                persons.add(Person("Vishal", "UNA", "Home Builder", R.drawable.homebuilder2,true,"876247645"))
            }
            else if(service == "AC Repair"){
                persons.add(Person("Harshit mishra", "UNA", "AC Repair", R.drawable.balajims,true,"9651908428"))
                persons.add(Person("Harsh Bhajwan", "UNA", "AC Repair", R.drawable.balajims,true,"876247645"))
            }
            else if(service == "Refrigerator Repair"){
                persons.add(Person("Shreyash", "UNA", "Fridge Repair", R.drawable.balajims,true,"456974562"))
                persons.add(Person("Kartik", "UNA", "Fridge Repair", R.drawable.balajims,true,"876247645"))
            }



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = MyAdapter(this, persons)
        recyclerView.adapter = adapter

        // This part should be inside onCreate after setting the content view
        val callButton: Button ? = findViewById(R.id.recyclerbutton)
        callButton?.setOnClickListener {
            val phoneNumber = "9119071696" // Replace with the desired phone number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }
}



















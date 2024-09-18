package com.example.loginsignupauth.Ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.loginsignupauth.Adapters.ServiceAdapter
import com.example.loginsignupauth.DataClasses.ServicePerson
import com.example.loginsignupauth.databinding.ActivityServiceBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityServiceBinding
    private lateinit var list: MutableList<ServicePerson> // Change to MutableList
    private lateinit var originalList: List<ServicePerson> // Store original list
    private lateinit var myAdapter: ServiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Spinner
        val arrayExperience = arrayOf(
            "Filter by Experience",
            "(0-2)Years",
            "(2-7)Years",
            "(7+)Years"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayExperience)
        binding.expFilterSpinner.adapter = adapter

        val selectedService = intent.getStringExtra("key")
        val databaseRef = FirebaseDatabase.getInstance().reference.child("Kapil").child(selectedService.toString())

        // Set up ValueEventListener
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list = mutableListOf()
                originalList = mutableListOf()
                for (childSnapshot in snapshot.children) {
                    val data = childSnapshot.getValue(ServicePerson::class.java)
                    data?.let {
                        list.add(it)
                        (originalList as MutableList<ServicePerson>).add(it)
                    }
                }
                if (list.isNotEmpty()) {
                    myAdapter = ServiceAdapter(this@ServiceActivity, list)
                    binding.rvService.adapter = myAdapter

                    // Set Spinner listener
                    binding.expFilterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            if (::myAdapter.isInitialized) {
                                if (position == 0) {
                                    myAdapter.resetToOriginalList()
                                } else {
                                    val selectedFilter = arrayExperience[position]
                                    myAdapter.filterByExperience(selectedFilter)
                                }
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            Toast.makeText(this@ServiceActivity, "Select to filter out by Experience", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "Error fetching data", error.toException())
                Toast.makeText(this@ServiceActivity, "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up SearchView listener
        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    myAdapter.filterByLocation(it)
                }
                return true
            }
        })

        // Add ValueEventListener to database reference
        databaseRef.addListenerForSingleValueEvent(valueEventListener)
    }


}

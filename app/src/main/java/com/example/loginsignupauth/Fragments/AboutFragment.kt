package com.example.loginsignupauth.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.loginsignupauth.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutBinding.inflate(inflater, container, false)

        // Retrieve the description from the hosting activity's intent
        val description = activity?.intent?.getStringExtra("desc")

        // Set the description to the TextView
        binding.AboutProvidertxt.text = description

        return binding.root
    }


}
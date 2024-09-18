package com.example.loginsignupauth.DataClasses


data class Person(
    val name: String,
    val location: String,
    val serviceCategory: String,
    val image: Int,
    val hasbutton: Boolean,
    val phone : String? = null

)


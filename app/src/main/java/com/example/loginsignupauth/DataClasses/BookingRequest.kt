package com.example.loginsignupauth.DataClasses

data class BookingRequest(
    val requestId: String,
    val userName: String,
    val bookingDate: String,
    val bookingTime: String,
    val bookingLocation: String,
    val status: BookingStatus
)

enum class BookingStatus {
    PENDING,
    ACCEPTED,
    DECLINED
}

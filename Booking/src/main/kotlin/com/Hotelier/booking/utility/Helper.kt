package com.Hotelier.booking.utility

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.exception.HotelierException
import java.net.URL

class Helper() {

    fun validate(dao: BookingDao) {
        val names = setOf("Free", "Offer", "Book", "Website")
        val category = setOf("Free", "Offer", "Book", "Website")
        if (dao.name in names) {
            throw HotelierException("Invalid Name")
        }
        if (dao.name.length < 10) {
            throw HotelierException("Name Length Should Be Greater Than 10")
        }
        Helper().isValidUrl(dao.image)

        if (dao.category in category) {
            throw HotelierException("Invalid Category")
        }


        if (dao.rating !in 0..5) {
            throw HotelierException("Rating Should Be Under Range 0 To 5");
        }
        if (dao.reputation !in 0..1000) {
            throw HotelierException("Reputation Should Be Under Range 0 To 1000");
        }
        if (dao.location.zipcode.toString().length < 5) {
            throw HotelierException("Zipcode Should Be Less Than 6 Digit");
        }

    }

    private fun isValidUrl(url: String): Boolean {
        try {
            URL(url)
            return true
        } catch (e: Exception) {
            throw HotelierException("Invalid Image URL")
        }
        return false
    }
}
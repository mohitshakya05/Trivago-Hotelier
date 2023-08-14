package com.Hotelier.booking.utility

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.exception.HotelierException
import org.slf4j.LoggerFactory
import java.net.URL

class Helper() {
    val logger = LoggerFactory.getLogger(this::class.java);

    fun validate(dao: BookingDao) {
        logger.info("Validation Stated")

        val names = setOf("Free", "Offer", "Book", "Website")
        val category = setOf("Free", "Offer", "Book", "Website")
        if (dao.name in names) {
            logger.error("Validation Fail By Name")
            throw HotelierException("Invalid Name")
        }
        if (dao.name.length < 10) {
            logger.error("Validation Fail By Name")
            throw HotelierException("Name Length Should Be Greater Than 10")

        }
        Helper().isValidUrl(dao.image)

        if (dao.category in category) {
            logger.error("Validation Fail By Category ")

            throw HotelierException("Invalid Category")
        }


        if (dao.rating !in 0..5) {
            logger.error("Validation Fail By rating ")

            throw HotelierException("Rating Should Be Under Range 0 To 5");
        }
        if (dao.reputation !in 0..1000) {
            logger.error("Validation Fail By reputation ")

            throw HotelierException("Reputation Should Be Under Range 0 To 1000");
        }
        if (dao.location.zipcode.toString().length < 5) {
            logger.error("Validation Fail By zipcode ")

            throw HotelierException("Zipcode Should Be Less Than 6 Digit");
        }

    }

    private fun isValidUrl(url: String): Boolean {
        try {
            URL(url)
            return true
        } catch (e: Exception) {
            logger.error("Validation Fail By  URL")
            throw HotelierException("Invalid Image URL")
        }
        return false
    }
}
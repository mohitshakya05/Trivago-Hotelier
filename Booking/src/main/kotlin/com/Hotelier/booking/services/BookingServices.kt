package com.Hotelier.booking.services

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.exception.HotelierException
import com.Hotelier.booking.repositry.BookingRepo
import com.Hotelier.booking.utility.Helper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookingServices(@Autowired private val bkRepo: BookingRepo) {
    val logger = LoggerFactory.getLogger(this::class.java);

    fun crateBooking(dao: BookingDao): BookingDao {
        logger.info("Enter In crateBooking")

        Helper().validate(dao);
        dao.badge = setBadge(dao.reputation)
        return bkRepo.save(dao);

    }

    fun getAllBooking(): List<BookingDao> = bkRepo.findAll();

    fun getBooking(id: Int): Optional<BookingDao> = bkRepo.findById(id);
    fun filterBookings(search: String): List<BookingDao> {
        logger.info("Enter In Data Filtering")

        val searchArr = search.split("=")
        if (searchArr.isEmpty()) {
            throw HotelierException("Please Insert Valid Data")
        }
        if (searchArr[0].lowercase() == "city") {
            return bkRepo.findAll().filter { it.location.city == searchArr[1] };
        }
        logger.info("Enter In Data Filtering Done")

        return bkRepo.fillterBookings(searchArr[1]);

    }

    fun bookingDeleteByIdBooking(id: Int): ResponseEntity<Void> {
        logger.info("Enter In bookingDeleteByIdBooking")

        val bookingData = bkRepo.findById(id)
        if (bookingData.isEmpty) {
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        } else {
            bkRepo.deleteById(id);
            return ResponseEntity<Void>(HttpStatus.OK)
        }
    }

    fun updateBooking(id: Int, dao: BookingDao): BookingDao {
        logger.info("Enter In updateBooking")

        val data = bkRepo.findById(id);
        Helper().validate(dao);
        if (bkRepo.findById(id).isEmpty) {
            throw HotelierException("Data Not Available")
        }
        val updated = data.get().copy(
            id,
            dao.name,
            dao.rating,
            dao.category,
            dao.image,
            dao.reputation,
            dao.badge,
            dao.price,
            dao.availability,
            dao.location
        )
        return bkRepo.save(updated);

    }

    fun setBadge(reputation: Int): String {
        if (reputation <= 500) {
            return "RED"
        } else if (reputation >= 799) {
            return "GREEN"
        }
        return "YELLOW"

    }
}
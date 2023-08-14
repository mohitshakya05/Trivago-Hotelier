package com.Hotelier.booking.services

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.exception.HotelierException
import com.Hotelier.booking.repositry.BookingRepo
import com.Hotelier.booking.utility.Helper
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

    fun crateBooking(dao: BookingDao): BookingDao {

        Helper().validate(dao);
        dao.badge = setBadge(dao.reputation)
        return bkRepo.save(dao);

    }

    fun getAllBooking(): List<BookingDao> = bkRepo.findAll();

    @Cacheable(cacheNames = ["booking"], key = "#id")
    fun getBooking(id: Int): Optional<BookingDao> = bkRepo.findById(id);
    fun filterBookings(search: String): List<BookingDao> {
        val searchArr = search.split("=")
        if (searchArr.isEmpty()) {
            throw HotelierException("Please Insert Valid Data")
        }
        if (searchArr[0].lowercase() == "city") {
            return bkRepo.findAll().filter { it.location.city == searchArr[1] };
        }
        return bkRepo.fillterBookings(searchArr[1]);

    }

    @CacheEvict(cacheNames = ["booking"], key = "#id")
    fun bookingDeleteByIdBooking(id: Int): ResponseEntity<Void> {
        val bookingData = bkRepo.findById(id)
        if (bookingData.isEmpty) {
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        } else {
            bkRepo.deleteById(id);
            return ResponseEntity<Void>(HttpStatus.OK)
        }
    }

    @CachePut(cacheNames = ["booking"], key = "#id")
    fun updateBooking(id: Int, dao: BookingDao): BookingDao {
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
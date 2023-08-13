package com.Hotelier.Booking.services

import com.Hotelier.Booking.Entity.BookingDao
import com.Hotelier.Booking.Exception.HotelierException
import com.Hotelier.Booking.Reposietry.BookingRepo
import com.Hotelier.Booking.Utility.Helper
import org.springframework.beans.factory.annotation.Autowired
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
    fun getBooking(id: Int): Optional<BookingDao> = bkRepo.findById(id);

    fun filterBookings(search:String): List<BookingDao> {
        val searchArr = search.split("=")
        if(searchArr.isEmpty()){
            throw HotelierException("Please Insert Valid Data")
        }
        if(searchArr[0].lowercase() == "city") {
            return bkRepo.findAll().filter { it.location.city == searchArr[1] };
        }
        return bkRepo.fillterBookings(searchArr[1]);

    }

    fun bookingDeleteByIdBooking(id: Int): ResponseEntity<Void> {
        val bookingData = bkRepo.findById(id)
        if (bookingData.isEmpty) {
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        } else {
            bkRepo.deleteById(id);
            return ResponseEntity<Void>(HttpStatus.OK)
        }
    }

    fun updateBooking(id: Int, dao: BookingDao): BookingDao {
        var data = bkRepo.findById(id);
        if (bkRepo.findById(id).isEmpty) {
            throw HotelierException("Data Not Available")
        }
        var updated = data.get().copy(
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
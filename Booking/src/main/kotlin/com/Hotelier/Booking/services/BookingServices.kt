package com.Hotelier.Booking.services

import com.Hotelier.Booking.Entity.BookingDao
import com.Hotelier.Booking.Exception.HotelierException
import com.Hotelier.Booking.Reposietry.BookingRepo
import com.Hotelier.Booking.Utility.Helper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.ObjectUtils
import java.util.*

@Service
class BookingServices(val bkRepo: BookingRepo) {

    fun crateBooking(dao: BookingDao): BookingDao {
        Helper().validate(dao);

        if (dao.reputation <= 500) {
            dao.badge = "RED";
        } else if (dao.reputation >= 799) {
            dao.badge = "GREEN";
        } else {
            dao.badge = "YELLOW";
        }
        return bkRepo.save(dao);
    }

    fun getBooking(id: Int): Optional<BookingDao> = bkRepo.findById(id);


    fun bookingDeleteByIdBooking(id: Int): ResponseEntity<Void> {
        val bookingData = bkRepo.findById(id)
        if (bookingData.isEmpty) {
            return ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        } else {
            bkRepo.deleteById(id);
            return ResponseEntity<Void>(HttpStatus.OK)
        }
    }
}
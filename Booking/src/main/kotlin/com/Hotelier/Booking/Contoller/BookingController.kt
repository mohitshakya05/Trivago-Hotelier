package com.Hotelier.Booking.Contoller

import com.Hotelier.Booking.Entity.BookingDao
import com.Hotelier.Booking.services.BookingServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/Booking")
class BookingController(val services: BookingServices) {


    @PostMapping("/create")
    fun create(@RequestBody dao: BookingDao): ResponseEntity<BookingDao> {
        val bookingData = services.crateBooking(dao);
        if (ObjectUtils.isEmpty(bookingData)) {
            return ResponseEntity<BookingDao>(HttpStatus.BAD_REQUEST)
        } else {
            return ResponseEntity(bookingData, HttpStatus.CREATED)
        }
    }

    @GetMapping("/getBooking/{id}")
    fun getBooking(id: Int): ResponseEntity<BookingDao> {
        val bookingData = services.getBooking(id);
        if (bookingData.isPresent) {
            return ResponseEntity<BookingDao>(bookingData.get(), HttpStatus.OK)
        }
        return ResponseEntity<BookingDao>(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/deleteBooking/{id}")
    fun deleteBooking(@PathVariable id: Int): ResponseEntity<Void> = services.bookingDeleteByIdBooking(id);

}
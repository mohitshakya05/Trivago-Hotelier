package com.Hotelier.booking.contoller

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.services.BookingServices
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ObjectUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/Hotelier")
class BookingController(val services: BookingServices) {

    val logger = LoggerFactory.getLogger(this::class.java);

    @PostMapping("/create")
    fun create(@RequestBody dao: BookingDao): ResponseEntity<BookingDao> {
        logger.info("Start Creating Hotelier")
        val bookingData = services.crateBooking(dao);
        if (ObjectUtils.isEmpty(bookingData)) {
            logger.error("Hotelier Unable  To Create",HttpStatus.CREATED)
            return ResponseEntity<BookingDao>(HttpStatus.BAD_REQUEST)
        } else {
            logger.info("Hotelier Created",HttpStatus.CREATED)
            return ResponseEntity(bookingData, HttpStatus.CREATED)
        }
    }

    @GetMapping("/getBooking/{id}")
    fun getBooking(@PathVariable id: Int): ResponseEntity<BookingDao> {
        val bookingData = services.getBooking(id);
        if (bookingData.isPresent) {
            return ResponseEntity<BookingDao>(bookingData.get(), HttpStatus.OK)
        }
        return ResponseEntity<BookingDao>(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/getAllBooking")
    fun getAllBooking(): List<BookingDao> = services.getAllBooking();

    @GetMapping("/filter")
    fun filterBooking(@RequestParam search:String): List<BookingDao> = services.filterBookings(search);

    @PutMapping("/update/{id}")
    fun updateBookingData(@PathVariable id:Int,@RequestBody dao: BookingDao): ResponseEntity<BookingDao> {
        val bookingData = services.updateBooking(id,dao);
        return ResponseEntity<BookingDao>(bookingData, HttpStatus.OK)
    }

    @DeleteMapping("/deleteBooking/{id}")
    fun deleteBooking(@PathVariable id: Int): ResponseEntity<Void> = services.bookingDeleteByIdBooking(id);

}
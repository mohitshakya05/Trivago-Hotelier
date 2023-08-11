package com.Hotelier.Booking.Reposietry

import com.Hotelier.Booking.Entity.BookingDao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookingRepo: JpaRepository<BookingDao, Int>
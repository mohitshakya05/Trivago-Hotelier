package com.Hotelier.Booking.Reposietry

import com.Hotelier.Booking.Entity.BookingDao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookingRepo : JpaRepository<BookingDao, Int> {
    @Query(
        "SELECT p FROM BookingDao p WHERE " +
                "p.name LIKE CONCAT('%',:query, '%')" +
                "Or p.category LIKE CONCAT('%', :query, '%')" + "Or p.rating LIKE CONCAT('%', :query, '%') " + "Or p.badge LIKE CONCAT('%', :query, '%') "
    )
    fun fillterBookings(query: String): List<BookingDao>
}
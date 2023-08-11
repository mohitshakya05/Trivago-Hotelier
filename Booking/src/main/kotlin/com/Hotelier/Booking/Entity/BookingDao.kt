package com.Hotelier.Booking.Entity

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import lombok.Data

@Entity
@Table(name = "BOOKING_DEF")
class BookingDao(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int,
    @Column(name = "name")
    val name: String,
    @field:Max(value = 1, message = "Age must be at least 18")
    @Column(name = "rating")
    val rating: Int,
    @Column(name = "category")
    val category: String,
    @Column(name = "image")
    val image: String,
    @Column(name = "reputation")
    val reputation: Int,
    @Column(name = "badge")
    var badge: String,
    @Column(name = "price")
    val price: Int,
    @Column(name = "availability")
    val availability: String,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "id")
    val location: LocationDao

)

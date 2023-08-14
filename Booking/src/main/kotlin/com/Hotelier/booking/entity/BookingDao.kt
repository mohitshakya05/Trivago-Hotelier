package com.Hotelier.booking.entity

import jakarta.persistence.*

@Entity
@Table(name = "BOOKING_DEF")
data class BookingDao(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int,
    @Column(name = "name")
    val name: String,
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
    val availability: Int,
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID", referencedColumnName = "id")
    val location: LocationDao
)


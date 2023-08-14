package com.Hotelier.booking.entity

import jakarta.persistence.*

@Entity
@Table(name="LOCATION")
data class LocationDao(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Int,

    @Column(name = "city")
    val city: String,

    @Column(name = "state")
    val state: String,

    @Column(name = "country")
    val country: String,

    @Column(name = "zipcode")
    val zipcode: Int,

    @Column(name = "address")
    val address: String

)
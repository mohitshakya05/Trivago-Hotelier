package com.Hotelier.Booking.Reposietry

import com.Hotelier.Booking.Entity.LocationDao
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepo:JpaRepository<LocationDao,Int>
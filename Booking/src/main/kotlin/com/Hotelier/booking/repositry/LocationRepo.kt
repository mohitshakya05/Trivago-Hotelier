package com.Hotelier.booking.repositry

import com.Hotelier.booking.entity.LocationDao
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepo:JpaRepository<LocationDao,Int>
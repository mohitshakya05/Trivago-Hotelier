package com.Hotelier.booking.Controller

import com.Hotelier.booking.entity.BookingDao
import com.Hotelier.booking.entity.LocationDao
import com.Hotelier.booking.services.BookingServices
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockBean
    lateinit var bookingSer : BookingServices;
    /*@Test
    fun getBookingsByIdTest() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/Hotelier/getBooking/2").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }*/
    @Test
    fun filterBookingTest() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/Hotelier/filter").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8).param("search","rating=5")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    fun createBookingTest() {
        val location = LocationDao(23,"Noida","Haryana","India",20801,"21 Street")
        val bookingDao = BookingDao(23,"Radisson blue Noid",3,"Hotel","image.com",300,"Green",200,23,location)
        var gson = Gson()
        var jsonString = gson.toJson(bookingDao)
        Mockito.`when`(bookingSer.crateBooking(bookingDao)).thenReturn(bookingDao);
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/Hotelier/create").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(jsonString)
                .accept(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isCreated);
    }

}
package com.dido.holidaybay.web;

import com.dido.holidaybay.model.dto.HotelDto;
import com.dido.holidaybay.model.dto.RoomDto;
import com.dido.holidaybay.model.enums.RoomTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    public static MockWebServer mockBackEnd;


    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start(7777);

        ObjectMapper objectMapper = new ObjectMapper();

        RoomDto roomDto = RoomDto.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .id(1L)
                .price(20d)
                .isFree(true)
                .imageUrl("")
                .number("1A")
                .build();

        HotelDto mockHotelDto = HotelDto.builder()
                .id(1L)
                .rooms(Collections.singletonList(roomDto))
                .name("hotel-test")
                .content("whatever str. 55")
                .build();

        Dispatcher dispatcher = new Dispatcher() {
            @SneakyThrows
            @NotNull
            @Override
            public MockResponse dispatch(@NotNull RecordedRequest recordedRequest) throws InterruptedException {
                if (recordedRequest.getMethod().equals("GET")
                        && recordedRequest.getPath().contains("/hotels/all/free-rooms")) {
                    return new MockResponse()
                            .setBody(objectMapper.writeValueAsString(Collections
                                    .singletonList(mockHotelDto)))
                            .addHeader("Content-Type", "application/json");
                }
                if (recordedRequest.getMethod().equals("GET")
                        && recordedRequest.getPath().contains("/rooms/2")) {
                    return new MockResponse()
                            .setBody(objectMapper.writeValueAsString(roomDto))
                            .addHeader("Content-Type", "application/json");
                }

                if (recordedRequest.getMethod().equals("POST")
                        && recordedRequest.getPath().contains("/rooms/update-status")) {
                    return new MockResponse().setBody(objectMapper.writeValueAsString(true))
                            .addHeader("Content-Type", "application/json");
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        mockBackEnd.setDispatcher(dispatcher);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }


    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testBooking() throws Exception {

        RoomDto roomDto = RoomDto.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .id(1L)
                .price(20d)
                .isFree(true)
                .imageUrl("")
                .number("1A")
                .build();

        HotelDto mockHotelDto = HotelDto.builder()
                .id(1L)
                .rooms(Collections.singletonList(roomDto))
                .name("hotel-test")
                .content("whatever str. 55")
                .build();


        mockMvc.perform(MockMvcRequestBuilders.get("/booking/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-booking"));
    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testAddBooking() throws Exception {

        RoomDto roomDto = RoomDto.builder()
                .roomType(RoomTypeEnum.ONE_BED_ECONOMY.name())
                .id(1L)
                .price(20d)
                .isFree(true)
                .imageUrl("")
                .number("1A")
                .build();

        HotelDto mockHotelDto = HotelDto.builder()
                .id(1L)
                .rooms(Collections.singletonList(roomDto))
                .name("hotel-test")
                .content("whatever str. 55")
                .build();


        mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .param("id", "1")
                        .param("roomId", "2")
                        .param("durationInNights", "3")
                        .param("price", "200")
                        .param("startDate", LocalDate.now().plusDays(3L).toString())
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/vouchers/all"))
                .andExpect(status().isFound());

    }

    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testAddBookingWrongDate() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .param("id", "1")
                        .param("roomId", "2")
                        .param("durationInNights", "3")
                        .param("price", "200")
                        .param("startDate", LocalDate.now().toString())
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/booking/add"))
                .andExpect(status().isFound());

    }


    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testAddBookingBigAmount() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .param("id", "1")
                        .param("roomId", "2")
                        .param("durationInNights", "1000")
                        .param("price", "200")
                        .param("startDate", LocalDate.now().plusDays(3L).toString())
                        .with(csrf())
                )
                .andExpect(redirectedUrl("/banking"))
                .andExpect(status().isFound());

    }


    @Test
    @WithMockUser(username = "admin@mail.com", roles = {"USER", "ADMIN"})
    void testBookingsHistory() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/booking/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("booking-history"));
    }


}

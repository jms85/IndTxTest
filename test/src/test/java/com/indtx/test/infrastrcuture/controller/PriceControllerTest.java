package com.indtx.test.infrastrcuture.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PriceControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private PriceController priceController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(priceController)
                .build();
    }

    @Test
    @SneakyThrows
    void expectedNotFound_whenBrandNotExists() {
        mockMvc.perform(get("/api/v1/brands/5/products/35455/prices?applicationDate=2020-06-1410:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void expectedNotFound_whenProductNotExists() {
        mockMvc.perform(get("/api/v1/brands/1/products/35456/prices?applicationDate=2020-06-1410:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void expectedNotFound_whenDateIsIncorrect() {
        mockMvc.perform(get("/api/v1/brands/5/products/35455/prices?applicationDate=xxx"))
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("usesCases")
    void shouldReturnPriceList_whenCallIsCorrect(Integer brandId, Integer productId, String localDateTime, List<ResponseDto> expectedResponse) {
        MvcResult result = mockMvc.perform(get("/api/v1/brands/" + brandId +
                        "/products/" + productId +
                        "/prices?applicationDate=" + localDateTime))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }

    private Stream<Arguments> usesCases() {
        return Stream.of(
                Arguments.of(1, 35455, "2020-06-1410:00", List.of(createExpectedDto(
                        1,
                        35455,
                        LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        35.5,
                        1,
                        "EUR"))),

                Arguments.of(1, 35455, "2020-06-1416:00", List.of(
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                            35.5,
                            1,
                            "EUR"),
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                            LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                            25.45,
                            2,
                            "EUR"))),

                Arguments.of(1, 35455, "2020-06-1421:00", List.of(createExpectedDto(
                        1,
                        35455,
                        LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                        LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                        35.5,
                        1,
                        "EUR"))),

                Arguments.of(1, 35455, "2020-06-1510:00", List.of(
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                            35.5,
                            1,
                            "EUR"),
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                            LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                            30.5,
                            3,
                            "EUR"))),

                Arguments.of(1, 35455, "2020-06-1621:00", List.of(
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                            35.5,
                            1,
                            "EUR"),
                        createExpectedDto(
                            1,
                            35455,
                            LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                            LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                            38.95,
                            4,
                            "EUR")))
        );
    }

    private ResponseDto createExpectedDto(Integer brandId, Integer productId, LocalDateTime startTime, LocalDateTime endTime, double price,
    Integer priceList, String currency) {
        return ResponseDto.builder()
                .brandId(brandId)
                .productId(productId)
                .startDate(startTime)
                .endDate(endTime)
                .price(price)
                .priceList(priceList)
                .currency(currency)
                .build();
    }
}
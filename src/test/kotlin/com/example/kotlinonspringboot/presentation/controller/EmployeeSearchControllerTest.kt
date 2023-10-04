package com.example.kotlinonspringboot.presentation.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeSearchControllerTest {

    @ParameterizedTest
    @CsvSource(
        delimiter = '|', textBlock =
        """
        # 番号     |エラーメッセージ
        a         |message 
        1000000000|message
        -1        |message
        """
    )
    @DisplayName("リクエストパラメータの番号が不正な場合に社員エラーレスポンスが返却されること")
    fun testWithWebTestClient(number: String, message: String, @Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/api/v1/employee/${number}")
            .exchange()
            .expectStatus().isBadRequest
            .expectBody().json(
                """
                {
                  "message": $message 
                }
            """
            )
    }
}

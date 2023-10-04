package com.example.kotlinonspringboot.integration.employee

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.annotation.Profile
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Profile("integration", "test")
class IntegrationGetEmployeeSearchControllerTest {
    @Test
    @DisplayName("")
    fun exampleTest(@Autowired webClient: WebTestClient) {
        webClient
                .get().uri("/rami/api/v1/employee")
                .exchange()
                .expectStatus().isOk
    }
}

package com.example.kotlinonspringboot.integration.actuator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {
    @Test
    fun exampleTest(@Autowired webClient: WebTestClient) {
        webClient
                .get().uri("/actuator/health")
                .exchange()
                .expectStatus().isOk
    }
}

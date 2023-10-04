package com.example.kotlinonspringboot.integration.actuator

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.annotation.Profile
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * なぜかActionsで動作しない
 */
@Profile("local-test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class It {
    @Test
    fun exampleTest(@Autowired webClient: WebTestClient) {
        webClient
            .get().uri("/actuator/health")
            .exchange()
            .expectStatus().isOk
    }
}

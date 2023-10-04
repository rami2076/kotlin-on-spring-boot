package com.example.kotlinonspringboot.integration.employee

import com.example.aaaaaaa.DatabaseContainerConfiguration
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationListEmployeeSearchControllerTest : DatabaseContainerConfiguration() {

    @Nested
    @DisplayName("データベースに登録済み社員が2名登録されている場合")
    @Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        statements = ["""
        INSERT INTO EMPLOYEE (EMPLOYEE_NUMBER, FULL_NAME, AGE, EMAIL_ADDRESS) VALUES (100, 'test1', 0, 'email@address.example');
        INSERT INTO EMPLOYEE (EMPLOYEE_NUMBER, FULL_NAME, AGE, EMAIL_ADDRESS) VALUES (1, 'test1', 0, 'email@address@example')
     """]
    )
    @Sql(
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        statements = ["TRUNCATE TABLE EMPLOYEE"]
    )
    internal inner class Case1 {

        @Nested
        @DisplayName("/v1/employee/をHTTPのGETメソッドでリクエストし成功した場合")
        internal inner class NestCase1 {

            @Test
            @DisplayName("HTTPステータス200が返却されること")
            fun test1(@Autowired webClient: WebTestClient) {
                webClient
                    .get().uri("/api/v1/employee")
                    .exchange()
                    .expectStatus().isOk
            }

            @Test
            @DisplayName("社員検索レスポンスが番号の昇順で返却されること")
            fun test2(@Autowired webClient: WebTestClient) {
                webClient
                    .get().uri("/api/v1/employee")
                    .exchange()
                    .expectBody().json(
                        """
                        {
                          "employees": [
                            {
                              "employee_number": 1,
                              "full_name": "test1",
                              "age": 0,
                              "email_address": "email@address1.example"
                            },
                            {
                              "employee_number": 100,
                              "full_name": "test1",
                              "age": 0,
                              "email_address": "email@address1.example"
                            }
                          ],
                          "total": 2
                        }"""
                    )
            }
        }
    }
}

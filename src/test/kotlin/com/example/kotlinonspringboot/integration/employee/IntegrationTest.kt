package com.example.kotlinonspringboot.integration.employee

import com.example.kotlinonspringboot.DatabaseContainerConfiguration
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@Disabled
@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("社員のIT試験")
class IntegrationTest : DatabaseContainerConfiguration() {

    @Nested
    @DisplayName("データベースに登録済み社員が2名登録されている場合")
    @Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        statements = [
            """
            INSERT INTO EMPLOYEE (EMPLOYEE_NUMBER, FULL_NAME, AGE, EMAIL_ADDRESS) VALUES (100, 'test1', 0, 'email@address.example');
            INSERT INTO EMPLOYEE (EMPLOYEE_NUMBER, FULL_NAME, AGE, EMAIL_ADDRESS) VALUES (1, 'test1', 0, 'email@address.example')"""
        ]
    )
    @Sql(
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
        statements = ["TRUNCATE TABLE EMPLOYEE"]
    )
    internal inner class Case1 {

        @Nested
        @DisplayName("全権検索 /v1/employee/をHTTPのGETメソッドでリクエストし成功した場合")
        internal inner class GetList {

            @Test
            @DisplayName("HTTPステータスが200で社員検索レスポンスが番号の昇順で返却されること")
            fun test1(@Autowired webClient: WebTestClient) {
                webClient
                    .get().uri("/api/v1/employee")
                    .exchange()
                    .expectStatus().isOk
                    .expectBody().json(
                        """
                                    {
                                      "employees": [
                                        {
                                          "employee_number": 1,
                                          "full_name": "test1",
                                          "age": 0,
                                          "email_address": "email@address.example"
                                        },
                                        {
                                          "employee_number": 100,
                                          "full_name": "test1",
                                          "age": 0,
                                          "email_address": "email@address.example"
                                        }
                                      ],
                                      "total": 2
                                    }"""
                    )
            }
        }

        @Nested
        @DisplayName("PK検索 /v1/employee/1で番号を指定してをHTTPのGETメソッドでリクエストし成功した場合")
        internal inner class GetSingle {

            @Test
            @DisplayName("HTTPステータスが200でパスパラメータで渡した番号1の社員が返却されること")
            fun test1(@Autowired webClient: WebTestClient) {
                webClient
                    .get().uri("/api/v1/employee/1")
                    .exchange()
                    .expectStatus().isOk
                    .expectBody().json(
                        """
                                    {
                                      "employees": [
                                        {
                                          "employee_number": 1,
                                          "full_name": "test1",
                                          "age": 0,
                                          "email_address": "email@address.example"
                                        }
                                      ],
                                      "total": 1
                                    }"""
                    )
            }
        }

        @Nested
        @DisplayName("PK削除 /v1/employee/1:パスパラメータに番号1でHTTPのDELETEメソッドでリクエストし成功した場合")
        internal inner class Delete {

            @Test
            @DisplayName("HTTPステータスが204であること")
            fun test1(@Autowired webClient: WebTestClient) {
                webClient
                    .delete().uri("/api/v1/employee/1")
                    .exchange()
                    .expectStatus().isNoContent
            }
        }

        @Nested
        @DisplayName("PK更新 /v1/employeeに社員更新リクエストをHTTPのPUTメソッドでリクエストし成功した場合")
        internal inner class Update {

            @Test
            @DisplayName("HTTPステータスが204であること")
            fun test1(@Autowired webClient: WebTestClient) {
                webClient
                    .put().uri("/api/v1/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(
                        """
                                {
                                  "employee": {
                                    "employee_number": 1,
                                    "full_name": "updated_full_name",
                                    "age": 10,
                                    "email_address": "update@example.com"
                                  }
                                }
                              """
                    )
                    .exchange()
                    .expectStatus().isNoContent
            }
        }
    }

    @Nested
    @DisplayName("PK登録 /v1/employeeに社員登録リクエストをHTTPのPOSTメソッドでリクエストし成功した場合")
    internal inner class Register {

        @Test
        @DisplayName("HTTPステータスが201であること")
        fun test1(@Autowired webClient: WebTestClient) {
            webClient
                .post().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "full_name": "name",
                      "age": 0,
                      "email_address": "mail@address"
                    }
                    """
                )
                .exchange()
                .expectStatus().isCreated
        }
    }
}

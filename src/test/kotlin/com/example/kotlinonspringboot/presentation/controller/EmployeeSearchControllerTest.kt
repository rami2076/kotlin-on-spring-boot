package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.EmployeeNumber
import com.example.kotlinonspringboot.domain.model.EmployeeServiceException
import com.example.kotlinonspringboot.domain.usecase.EmployeeSearchUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("社員検索コントローラのテスト")
class EmployeeSearchControllerTest {

    @MockBean
    private lateinit var employeeSearchUseCase: EmployeeSearchUseCase

    @Nested
    @DisplayName("PK社員検索")
    inner class List {
        @ParameterizedTest(name = "パスパラメータの番号が{0}の時、社員エラーレスポンスが返却されること")
        @CsvSource(
            delimiter = '|',
            textBlock =
            // 説明     | 番号      |エラーメッセージ
            """
            文字種違反  |a          |Failed to convert value of type 'java.lang.String' to required type 'java.math.BigDecimal'; Character a is neither a decimal digit number, decimal point, nor \"e\" notation exponential mark. 
            閾値超過    |1000000000 |get.employeeNumber: must be less than or equal to 999999999
            閾値未満    |-1         |get.employeeNumber: must be greater than or equal to 0"""
        )
        @DisplayName("BadRequestのテスト")
        fun test1(
            description: String,
            number: String,
            message: String,
            @Autowired webClient: WebTestClient
        ) {
            // 実行 & 検証
            webClient
                .get().uri("/api/v1/employee/$number")
                .exchange()
                .expectStatus().isBadRequest
                .expectBody().json(
                    """
                                {
                                  "message": "$message" 
                                }"""
                )
        }

        @ParameterizedTest(name = "パスパラメータの番号の{0}である{1}の時、社員検索レスポンスが返却されること")
        @CsvSource(
            delimiter = '|',
            textBlock =
            // 説明     | 番号
            """
            minimum    |0 
            maximum    |999999999"""
        )
        @DisplayName("OKのテスト")
        fun test2(
            description: String,
            number: String,
            @Autowired webClient: WebTestClient
        ) {
            // 準備
            // 社員検索ユースケースからの返却を定義
            val employee = Employee.RegisteredEmployee(
                employeeNumber = EmployeeNumber(number.toLong()),
                fullName = "test1",
                age = 0,
                emailAddress = "email@address1.example"
            )
            whenever(employeeSearchUseCase.sortedSearch(any())).doReturn(listOf(employee))

            // 実行 & 検証
            webClient
                .get().uri("/api/v1/employee/$number")
                .exchange()
                .expectStatus().isOk
                .expectBody().json(
                    """
                                {
                                  "employees": [
                                    {
                                      "employee_number": $number,
                                      "full_name": "test1",
                                      "age": 0,
                                      "email_address": "email@address1.example"
                                    }
                                  ],
                                  "total": 1
                                }
                                """
                )
        }

        @Test
        @DisplayName("検索結果が0件の場合、OKを返し、社員検索リクエストを返却する")
        fun test3(
            @Autowired webClient: WebTestClient
        ) {
            // 準備
            // 社員検索ユースケースからの返却を定義
            whenever(employeeSearchUseCase.sortedSearch(any())).doReturn(emptyList())

            // 実行 & 検証
            webClient
                .get().uri("/api/v1/employee/1")
                .exchange()
                .expectStatus().isOk
                .expectBody().json(
                    """
                                {
                                  "employees": [],
                                  "total": 0
                                }
                                """
                )
        }

        @Test
        @DisplayName("ユースケース実行時にEmployeeDataSourceExceptionが発生した場合、500を返却すること")
        fun test4(
            @Autowired webClient: WebTestClient
        ) {
            // 準備
            // 社員検索ユースケースからの返却を定義
            whenever(employeeSearchUseCase.sortedSearch(any())).thenThrow(
                EmployeeServiceException.EmployeeDataSourceException(
                    RuntimeException("something error message")
                )
            )

            // 実行 & 検証
            webClient
                .get().uri("/api/v1/employee/1")
                .exchange()
                .expectStatus().isEqualTo(500)
                .expectBody().json(
                    """
                                {
                                  "message": "databaseで予期しない例外が発生しました" 
                                }"""
                )
        }
    }
}

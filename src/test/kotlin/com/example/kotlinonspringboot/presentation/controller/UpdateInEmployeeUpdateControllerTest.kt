package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.EmployeeServiceException
import com.example.kotlinonspringboot.domain.usecase.EmployeeUpdateUseCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.kotlin.any
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

import java.util.stream.Stream

/**
 * メソッドが多いのでテストクラスを分割している
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PK社員更新コントローラのテスト")
class UpdateInEmployeeUpdateControllerTest {

    @MockBean
    private lateinit var employeeUpdateUseCase: EmployeeUpdateUseCase

    @BeforeEach
    fun setUp() {
        // 準備
        // 社員更新ユースケースからの返却を定義
        whenever(employeeUpdateUseCase.update(any())).thenReturn(true)
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("社員更新")
    inner class Update {

        @Test
        @DisplayName("更新時に、例外が発生しない場合は204を返却する")
        fun test1(@Autowired webClient: WebTestClient) {
            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "full_name",
                        "age": 20,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isNoContent
        }

        @Test
        @DisplayName("社員データソース例外が発生した場合、ステータス500をが返却すること")
        fun test2(@Autowired webClient: WebTestClient) {
            // 準備
            // 社員更新ユースケースからの返却を定義
            val runtimeException = RuntimeException("something error message")
            val exception = EmployeeServiceException.EmployeeDataSourceException(runtimeException)
            whenever(employeeUpdateUseCase.update(any())).doThrow(exception)

            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "full_name",
                        "age": 20,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().is5xxServerError
                .expectStatus().isEqualTo(500)
                .expectBody().json(
                    """
                                {
                                  "message": "databaseで予期しない例外が発生しました" 
                                }"""
                )
        }

        @Test
        @DisplayName("社員該当なし例外が発生した場合、ステータス410をが返却すること")
        fun test2_1(@Autowired webClient: WebTestClient) {
            // 準備
            // 社員更新ユースケースからの返却を定義
            val runtimeException = RuntimeException("something error message")
            val exception = EmployeeServiceException.EmployeeUpdateNotApplicableException(runtimeException)
            whenever(employeeUpdateUseCase.update(any())).doThrow(exception)

            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "full_name",
                        "age": 20,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isEqualTo(410)
                .expectBody().json(
                    """
                                {
                                  "message": "社員更新中に対象の番号の社員が該当しませんでした" 
                                }"""
                )
        }

        @ParameterizedTest(name = "番号が[{0}]の時ステータス{2}を返却すること")
        @DisplayName("番号の網羅試験")
        @MethodSource("test3_0Source")
        fun test3_0(
            description: String,
            employeeNumber: String,
            httpStatus: Int,
            @Autowired webClient: WebTestClient
        ) {
            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": $employeeNumber,
                        "full_name": "full_name",
                        "age": 20,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isEqualTo(httpStatus)
        }

        private fun test3_0Source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("閾値最小", "0", 204),
                Arguments.of("閾値最大", "999999999", 204),
                Arguments.of("閾値超過", "1000000000", 400),
                Arguments.of("閾値未満", "-1", 400),
                Arguments.of("形式エラー", "\"a\"", 400)
            )
        }

        @ParameterizedTest(name = "氏名が[{0}]の時ステータス{2}を返却すること")
        @DisplayName("氏名の網羅試験")
        @MethodSource("test3Source")
        fun test3(
            description: String,
            fullName: String,
            httpStatus: Int,
            @Autowired webClient: WebTestClient
        ) {
            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "$fullName",
                        "age": 20,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isEqualTo(httpStatus)
        }

        private fun test3Source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("閾値最小", "1".repeat(2), 204),
                Arguments.of("閾値最大", "1".repeat(100), 204),
                Arguments.of("閾値超過", "1".repeat(101), 400),
                Arguments.of("閾値未満", "1".repeat(1), 400)
            )
        }

        @ParameterizedTest(name = "年齢が[{0}]の時ステータス{2}を返却すること")
        @DisplayName("年齢の網羅試験")
        @MethodSource("test4Source")
        fun test4(
            description: String,
            age: String,
            httpStatus: Int,
            @Autowired webClient: WebTestClient
        ) {
            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "full_Name",
                        "age": $age,
                        "email_address": "email@address.example"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isEqualTo(httpStatus)
        }

        private fun test4Source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("閾値最小", "0", 204),
                Arguments.of("閾値最大", "200", 204),
                Arguments.of("閾値超過", "201", 400),
                Arguments.of("閾値未満", "-1", 400)
            )
        }

        @ParameterizedTest(name = "メールアドレスが[{0}]の時ステータス{2}を返却すること")
        @DisplayName("メールアドレスの網羅試験")
        @MethodSource("test5Source")
        fun test5(
            description: String,
            emailAddress: String,
            httpStatus: Int,
            @Autowired webClient: WebTestClient
        ) {
            // 実行 & 検証
            webClient.put().uri("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(
                    """
                    {
                      "employee": {
                        "employee_number": 1,
                        "full_name": "full_Name",
                        "age": 20,
                        "email_address": "$emailAddress"
                      }
                    }
                    """.trimIndent()
                ).exchange()
                .expectStatus().isEqualTo(httpStatus)
        }

        private fun test5Source(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("閾値最小(10)", "a@ex.apple", 204),
                Arguments.of("閾値未満(9)", "a@exapple", 400),
                Arguments.of("使用可能文字", "abcdefghijklmnopqrstuvwxyz1234567890-_@exapple", 204)
            )
        }
    }
}

package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.EmployeeServiceException
import com.example.kotlinonspringboot.domain.usecase.EmployeeDeleteUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

/**
 * メソッドが多いのでテストクラスを分割している
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("社員削除コントローラのテスト")
class DeleteInEmployeeUpdateControllerTest {

    @MockBean
    private lateinit var employeeDeleteUseCase: EmployeeDeleteUseCase

    @Nested
    @DisplayName("PK社員削除")
    inner class Delete {

        @ParameterizedTest(name = "削除対象:{1}の場合、例外が発生しない場合は204を返却する")
        @CsvSource(
            delimiter = '|',
            textBlock =
            // 削除対象の有無
            """
            true  |削除あり
            false |削除対象なし"""
        )
        @DisplayName("削除時に例外が出ない場合、204を返却すること")
        fun test1(isDelete: Boolean, description: String, @Autowired webClient: WebTestClient) {
            // 準備
            // 社員削除ユースケースからの返却を定義
            whenever(employeeDeleteUseCase.delete(any())).doReturn(isDelete)

            // 実行 & 検証
            webClient
                .delete().uri("/api/v1/employee/1")
                .exchange()
                .expectStatus().isNoContent
        }

        @Test
        @DisplayName("社員データソース例外が発生した場合、ステータス500をが返却すること")
        fun test2(@Autowired webClient: WebTestClient) {
            // 準備
            // 社員削除ユースケースからの返却を定義
            val runtimeException = RuntimeException("something error message")
            val exception = EmployeeServiceException.EmployeeDataSourceException(runtimeException)
            whenever(employeeDeleteUseCase.delete(any())).doThrow(exception)

            // 実行 & 検証
            webClient
                .delete().uri("/api/v1/employee/1")
                .exchange()
                .expectStatus().is5xxServerError
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

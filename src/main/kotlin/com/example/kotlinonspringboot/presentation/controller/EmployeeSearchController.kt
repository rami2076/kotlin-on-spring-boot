package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeSearchUseCase
import com.example.kotlinonspringboot.presentation.api.EmployeeSearchApi
import com.example.kotlinonspringboot.presentation.factory.EmployeeSearchResponseFactory
import com.example.kotlinonspringboot.presentation.model.EmployeeSearchResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@Validated
class EmployeeSearchController(private val employeeSearchUseCase: EmployeeSearchUseCase) : EmployeeSearchApi {

    override fun list(): ResponseEntity<EmployeeSearchResponse> {
        // 検索
        val searchAllCondition = SearchCondition.EmployeeAllSearchCondition
        val registeredEmployees = employeeSearchUseCase.sortedSearch(searchAllCondition)

        // レスポンス作成
        val employeeSearchResponse = when {
            registeredEmployees.isEmpty() -> EmployeeSearchResponseFactory.empty()
            registeredEmployees.size == 1 -> EmployeeSearchResponseFactory.single(registeredEmployees.single())
            else -> EmployeeSearchResponseFactory.list(registeredEmployees)
        }

        return ResponseEntity(employeeSearchResponse, HttpStatus.OK)
    }

    override fun get(employeeNumber: BigDecimal): ResponseEntity<EmployeeSearchResponse> {
        // 検索
        val pkSearchCondition = SearchCondition.EmployeePKSearchCondition(
            employeeNumber.toLong()
        )
        val registeredEmployees = employeeSearchUseCase.sortedSearch(pkSearchCondition)

        // レスポンス作成
        val employeeSearchResponse = when (registeredEmployees.isEmpty()) {
            true -> EmployeeSearchResponseFactory.empty()
            false -> EmployeeSearchResponseFactory.single(registeredEmployees.single())
        }

        return ResponseEntity(employeeSearchResponse, HttpStatus.OK)
    }
}

package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeSearchUseCase
import com.example.kotlinonspringboot.presentation.api.EmployeeSearchApi
import com.example.kotlinonspringboot.presentation.factory.EmployeeSearchResponseFactory
import com.example.kotlinonspringboot.presentation.model.Employee
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
        val employees = listOf(
            Employee(
                employeeNumber = BigDecimal.valueOf(1),
                fullName = "test1",
                age = BigDecimal.ZERO,
                emailAddress = "email@address1.example"
            ),
            Employee(
                employeeNumber = BigDecimal.valueOf(100),
                fullName = "test1",
                age = BigDecimal.ZERO,
                emailAddress = "email@address1.example"
            )
        )
        val response = EmployeeSearchResponse(
            employees = employees,
            total = BigDecimal.valueOf(2)
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    override fun get(employeeNumber: BigDecimal): ResponseEntity<EmployeeSearchResponse> {
        val pkSearchCondition = SearchCondition.EmployeePKSearchCondition(
            employeeNumber.toLong()
        )

        val registeredEmployees = employeeSearchUseCase.sortedSearch(pkSearchCondition)

        val employeeSearchResponse = when (registeredEmployees.isEmpty()) {
            true -> EmployeeSearchResponseFactory.empty()
            false -> EmployeeSearchResponseFactory.single(registeredEmployees.single())
        }

        return ResponseEntity(employeeSearchResponse, HttpStatus.OK)
    }
}

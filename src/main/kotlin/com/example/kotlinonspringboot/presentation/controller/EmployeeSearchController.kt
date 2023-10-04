package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.presentation.api.EmployeeSearchApi
import com.example.kotlinonspringboot.presentation.model.Employee
import com.example.kotlinonspringboot.presentation.model.EmployeeSearchResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class EmployeeSearchController : EmployeeSearchApi {

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
        val response = EmployeeSearchResponse(employees = employees, total = BigDecimal.valueOf(2))

        return ResponseEntity(response, HttpStatus.OK)
    }
}

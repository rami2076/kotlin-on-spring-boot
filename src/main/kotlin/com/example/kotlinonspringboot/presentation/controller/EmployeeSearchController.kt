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
        val employees = listOf(Employee(BigDecimal.ONE, "name", BigDecimal.ONE, "name"))
        val response = EmployeeSearchResponse(employees, BigDecimal.ONE)

        return ResponseEntity(response, HttpStatus.OK)
    }
}

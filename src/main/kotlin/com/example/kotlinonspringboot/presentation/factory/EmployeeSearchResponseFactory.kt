package com.example.kotlinonspringboot.presentation.factory

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.presentation.model.EmployeeSearchResponse
import java.math.BigDecimal

class EmployeeSearchResponseFactory {
    companion object {
        fun empty(): EmployeeSearchResponse {
            return EmployeeSearchResponse(
                employees = emptyList(),
                total = BigDecimal.ZERO,
            )
        }

        fun single(registeredEmployee: Employee.RegisteredEmployee): EmployeeSearchResponse {
            val employee = EmployeeConverter.from(registeredEmployee)

            return EmployeeSearchResponse(
                employees = listOf(employee),
                total = BigDecimal.ONE,
            )
        }

        fun list(registeredEmployees: List<Employee.RegisteredEmployee>): EmployeeSearchResponse {
            val employees = registeredEmployees.map(EmployeeConverter::from)

            return EmployeeSearchResponse(
                employees = employees,
                total = employees.size.toBigDecimal(),
            )
        }
    }
}

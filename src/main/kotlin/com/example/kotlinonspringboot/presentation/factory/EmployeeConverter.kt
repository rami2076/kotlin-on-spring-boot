package com.example.kotlinonspringboot.presentation.factory

import com.example.kotlinonspringboot.domain.model.Employee
import java.math.BigDecimal

class EmployeeConverter {

    companion object {

        fun from(registeredEmployee: Employee.RegisteredEmployee): com.example.kotlinonspringboot.presentation.model.Employee {
            return com.example.kotlinonspringboot.presentation.model.Employee(
                employeeNumber = registeredEmployee.employeeNumber.value.toBigDecimal(),
                fullName = registeredEmployee.fullName,
                age = BigDecimal(registeredEmployee.age.toInt()),
                emailAddress = registeredEmployee.emailAddress
            )
        }
    }
}

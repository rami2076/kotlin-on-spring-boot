package com.example.kotlinonspringboot.domain.model

sealed interface Employee {
    data class UnRegisteredEmployee(
        val fullName: String,
        val age: Short,
        val emailAddress: String,
    ) : Employee

    data class RegisteredEmployee(
        val employeeNumber: EmployeeNumber,
        val fullName: String,
        val age: Short,
        val emailAddress: String,
    ) : Employee

    data class WillRenewEmployee(
        val employeeNumber: EmployeeNumber,
        val fullName: String,
        val age: Short,
        val emailAddress: String,
    ) : Employee
}

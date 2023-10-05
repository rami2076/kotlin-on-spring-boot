package com.example.kotlinonspringboot.infrastructure.employee.datasource.entity

data class EmployeeEntity(
    val employeeNumber: Long,
    val fullName: String,
    val age: Short,
    val emailAddress: String
)

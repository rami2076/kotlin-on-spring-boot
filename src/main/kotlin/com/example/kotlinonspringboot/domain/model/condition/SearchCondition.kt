package com.example.kotlinonspringboot.domain.model.condition

sealed interface SearchCondition {
    data class EmployeePKSearchCondition(val employeeNumber: Long) : SearchCondition

    object EmployeeAllSearchCondition : SearchCondition
}

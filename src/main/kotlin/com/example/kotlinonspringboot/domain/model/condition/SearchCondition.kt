package com.example.kotlinonspringboot.domain.model.condition

import com.example.kotlinonspringboot.domain.model.EmployeeNumber

sealed interface SearchCondition {
    data class EmployeePKSearchCondition(val employeeNumber: EmployeeNumber) : SearchCondition

    object EmployeeAllSearchCondition : SearchCondition
}

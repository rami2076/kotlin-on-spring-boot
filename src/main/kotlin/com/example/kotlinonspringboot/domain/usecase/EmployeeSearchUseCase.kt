package com.example.kotlinonspringboot.domain.usecase

import com.example.kotlinonspringboot.domain.model.Employee.RegisteredEmployee
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition

interface EmployeeSearchUseCase {
    fun sortedSearch(searchCondition: SearchCondition): List<RegisteredEmployee>
}

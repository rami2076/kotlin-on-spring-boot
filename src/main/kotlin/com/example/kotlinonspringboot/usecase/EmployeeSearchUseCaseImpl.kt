package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.EmployeeNumber
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeSearchUseCase
import org.springframework.stereotype.Service

@Service
class EmployeeSearchUseCaseImpl :
    EmployeeSearchUseCase {

    override fun sortedSearch(searchCondition: SearchCondition): List<Employee.RegisteredEmployee> {
        val employee1 = Employee.RegisteredEmployee(
            employeeNumber = EmployeeNumber(1L),
            fullName = "test1",
            age = 0,
            emailAddress = "email@address1.example"
        )
        val employee2 = Employee.RegisteredEmployee(
            employeeNumber = EmployeeNumber(100L),
            fullName = "test1",
            age = 0,
            emailAddress = "email@address1.example"
        )

        return when (searchCondition) {
            is SearchCondition.EmployeeAllSearchCondition -> listOf(employee1, employee2)
            is SearchCondition.EmployeePKSearchCondition -> listOf(employee1)
        }
    }
}

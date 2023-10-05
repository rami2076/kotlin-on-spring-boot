package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.domain.usecase.EmployeeSearchUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeSearchUseCaseImpl(private val employeeRepository: EmployeeRepository) : EmployeeSearchUseCase {

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    override fun sortedSearch(searchCondition: SearchCondition): List<Employee.RegisteredEmployee> {
        return when (searchCondition) {
            is SearchCondition.EmployeeAllSearchCondition -> employeeRepository.findAllSortedByKey()
            is SearchCondition.EmployeePKSearchCondition -> findByKey(searchCondition)
        }
    }

    @Transactional(readOnly = true, rollbackFor = [Exception::class])
    internal fun findByKey(employeePKSearchCondition: SearchCondition.EmployeePKSearchCondition): List<Employee.RegisteredEmployee> {
        return when (val registeredEmployee = employeeRepository.findByKey(employeePKSearchCondition)) {
            null -> emptyList()
            else -> listOf(registeredEmployee)
        }
    }
}

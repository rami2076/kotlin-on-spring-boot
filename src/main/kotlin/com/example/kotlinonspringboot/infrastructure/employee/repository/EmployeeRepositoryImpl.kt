package com.example.kotlinonspringboot.infrastructure.employee.repository

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.infrastructure.employee.datasource.mapper.EmployeeMapper
import org.springframework.stereotype.Repository

@Repository
class EmployeeRepositoryImpl(private val employeeMapper: EmployeeMapper) : EmployeeRepository {
    override fun save(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * キーの昇順でソートした登録済みの全社員を取得
     */
    override fun findAllSortedByKey(): List<Employee.RegisteredEmployee> {
        TODO("Not yet implemented")
    }

    override fun findByKey(searchPKSearchCondition: SearchCondition.EmployeePKSearchCondition): Employee.RegisteredEmployee? {
        TODO("Not yet implemented")
    }

    override fun deleteByKey(deleteCondition: DeleteCondition): Boolean {
        TODO("Not yet implemented")
    }
}

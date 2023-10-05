package com.example.kotlinonspringboot.domain.repository

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition

interface EmployeeRepository {

    /**
     * 未登録社員の登録
     * @param unRegisteredEmployee 未登録社員
     * @return true, never.
     */
    fun save(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean

    /**
     * 登録社員の更新
     * @param willRenewEmployee 更新予定社員
     * @return true, never.
     */
    fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean

    /**
     * 登録社員全権取得
     * @return Case found Employees: Type: List<RegisteredEmployee>.
     * Case not found Employees: Type: EmptyList
     */
    fun findAllSortedByKey(): List<Employee.RegisteredEmployee>

    /**
     * PK社員取得
     *
     * @return Case found Employee: Type: RegisteredEmployee.
     * Case not found Employees: Type: Nullable RegisteredEmployee
     */
    fun findByKey(searchPKSearchCondition: SearchCondition.EmployeePKSearchCondition): Employee.RegisteredEmployee?
}

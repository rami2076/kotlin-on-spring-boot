package com.example.kotlinonspringboot.domain.usecase

import com.example.kotlinonspringboot.domain.model.Employee

interface EmployeeUpdateUseCase {
    /**
     * 更新ユースケース
     * @param willRenewEmployee 更新予定社員
     * @return 常にtrue
     */
    fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean
}

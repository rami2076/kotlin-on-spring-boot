package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.usecase.EmployeeUpdateUseCase
import org.springframework.stereotype.Service

@Service
class EmployeeUpdateUseCaseImpl : EmployeeUpdateUseCase {

    /**
     * 更新ユースケース
     * @param willRenewEmployee 更新予定社員
     * @return 常にtrue
     */
    override fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean {
        return true
    }
}

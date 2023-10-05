package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.usecase.EmployeeRegisterUseCase
import org.springframework.stereotype.Service

@Service
class EmployeeRegisterUseCaseImpl : EmployeeRegisterUseCase {

    /**
     * 登録ユースケース
     * @param unRegisteredEmployee 未登録社員
     * @return 常にtrue
     */
    override fun register(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean {
        return true
    }
}

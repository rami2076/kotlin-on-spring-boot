package com.example.kotlinonspringboot.domain.usecase

import com.example.kotlinonspringboot.domain.model.Employee

interface EmployeeRegisterUseCase {

    /**
     * 登録ユースケース
     * @param unRegisteredEmployee 未登録社員
     * @return 常にtrue
     */
    fun register(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean
}

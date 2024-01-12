package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.domain.usecase.EmployeeRegisterUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeRegisterUseCaseImpl(private val employeeRepository: EmployeeRepository) : EmployeeRegisterUseCase {
    /**
     * 登録ユースケース
     * @param unRegisteredEmployee 未登録社員
     * @return 常にtrue
     */
    @Transactional(readOnly = false, rollbackFor = [Exception::class])
    override fun register(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean {
        return employeeRepository.save(unRegisteredEmployee)
    }
}

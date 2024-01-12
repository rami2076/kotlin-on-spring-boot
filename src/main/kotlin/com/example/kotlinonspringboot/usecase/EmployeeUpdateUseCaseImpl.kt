package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.domain.usecase.EmployeeUpdateUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeUpdateUseCaseImpl(private val employeeRepository: EmployeeRepository) : EmployeeUpdateUseCase {
    /**
     * 更新ユースケース
     * @param willRenewEmployee 更新予定社員
     * @return 常にtrue
     */
    @Transactional(readOnly = false, rollbackFor = [Exception::class])
    override fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean {
        return employeeRepository.update(willRenewEmployee)
    }
}

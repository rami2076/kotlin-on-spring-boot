package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.domain.usecase.EmployeeDeleteUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeDeleteUseCaseImpl(private val employeeRepository: EmployeeRepository) : EmployeeDeleteUseCase {
    /**
     * PK社員削除
     * @param deleteCondition 削除条件
     * @return true, delete target exist and deleted.
     * false, delete target absent and had deleted it.
     */
    @Transactional(readOnly = false, rollbackFor = [Exception::class])
    override fun delete(deleteCondition: DeleteCondition): Boolean {
        return employeeRepository.deleteByKey(deleteCondition)
    }
}

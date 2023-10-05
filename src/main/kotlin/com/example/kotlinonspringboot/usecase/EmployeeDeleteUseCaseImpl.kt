package com.example.kotlinonspringboot.usecase

import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeDeleteUseCase
import org.springframework.stereotype.Service

@Service
class EmployeeDeleteUseCaseImpl : EmployeeDeleteUseCase {

    override fun delete(deleteCondition: DeleteCondition): Boolean {
        return true
    }
}

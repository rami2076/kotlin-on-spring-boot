package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.EmployeeNumber
import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeDeleteUseCase
import com.example.kotlinonspringboot.presentation.api.EmployeeUpdateApi
import com.example.kotlinonspringboot.presentation.model.EmployeeRegisterRequest
import com.example.kotlinonspringboot.presentation.model.EmployeeUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class EmployeeUpdateController(
    private val employeeDeleteUseCase: EmployeeDeleteUseCase
) : EmployeeUpdateApi {

    override fun register(employeeRegisterRequest: EmployeeRegisterRequest): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun delete(employeeNumber: BigDecimal): ResponseEntity<Unit> {
        val deleteCondition = DeleteCondition(EmployeeNumber(employeeNumber.toLong()))

        employeeDeleteUseCase.delete(deleteCondition)

        return ResponseEntity.noContent().build()
    }

    override fun update(employeeUpdateRequest: EmployeeUpdateRequest): ResponseEntity<Unit> {
        return ResponseEntity.noContent().build()
    }
}

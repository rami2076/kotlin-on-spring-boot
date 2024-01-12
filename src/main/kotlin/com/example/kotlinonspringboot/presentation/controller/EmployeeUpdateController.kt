package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.EmployeeNumber
import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.usecase.EmployeeDeleteUseCase
import com.example.kotlinonspringboot.domain.usecase.EmployeeRegisterUseCase
import com.example.kotlinonspringboot.domain.usecase.EmployeeUpdateUseCase
import com.example.kotlinonspringboot.presentation.api.EmployeeUpdateApi
import com.example.kotlinonspringboot.presentation.model.EmployeeRegisterRequest
import com.example.kotlinonspringboot.presentation.model.EmployeeUpdateRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
class EmployeeUpdateController(
    private val employeeDeleteUseCase: EmployeeDeleteUseCase,
    private val employeeRegisterUseCase: EmployeeRegisterUseCase,
    private val employeeUpdateUseCase: EmployeeUpdateUseCase,
) : EmployeeUpdateApi {
    override fun register(employeeRegisterRequest: EmployeeRegisterRequest): ResponseEntity<Unit> {
        // 変換
        val (fullName, age, emailAddress) = employeeRegisterRequest
        val unRegisterEmployee =
            Employee.UnRegisteredEmployee(
                fullName = fullName,
                age = age.shortValueExact(),
                emailAddress = emailAddress,
            )

        employeeRegisterUseCase.register(unRegisterEmployee)

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    override fun delete(employeeNumber: BigDecimal): ResponseEntity<Unit> {
        val deleteCondition = DeleteCondition(EmployeeNumber(employeeNumber.toLong()))

        employeeDeleteUseCase.delete(deleteCondition)

        return ResponseEntity.noContent().build()
    }

    override fun update(employeeUpdateRequest: EmployeeUpdateRequest): ResponseEntity<Unit> {
        // 変換
        val (employeeNumber, fullName, age, emailAddress) = employeeUpdateRequest.employee
        val willRenewEmployee =
            Employee.WillRenewEmployee(
                employeeNumber = EmployeeNumber(employeeNumber.toLong()),
                fullName = fullName,
                age = age.shortValueExact(),
                emailAddress = emailAddress,
            )

        employeeUpdateUseCase.update(willRenewEmployee)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}

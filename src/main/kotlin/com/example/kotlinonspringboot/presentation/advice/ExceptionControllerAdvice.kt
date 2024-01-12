package com.example.kotlinonspringboot.presentation.advice

import com.example.kotlinonspringboot.domain.model.EmployeeServiceException
import com.example.kotlinonspringboot.presentation.model.EmployeeServiceErrorResponse
import jakarta.validation.ConstraintViolationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.event.Level
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ExceptionControllerAdvice {
    val logger: Logger = LoggerFactory.getLogger(ExceptionControllerAdvice::class.java)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleIllegalStateException(ex: ConstraintViolationException): ResponseEntity<EmployeeServiceErrorResponse> {
        logger.info("不適格なリクエストを検知しました", ex)
        return badRequest(ex.message)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleIllegalStateException(ex: MethodArgumentTypeMismatchException): ResponseEntity<EmployeeServiceErrorResponse> {
        logger.info("不適格なリクエストを検知しました", ex)
        return badRequest(ex.message)
    }

    internal fun badRequest(message: String?): ResponseEntity<EmployeeServiceErrorResponse> {
        val errorResponse =
            when (message) {
                null -> EmployeeServiceErrorResponse()
                else -> EmployeeServiceErrorResponse(message)
            }
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(EmployeeServiceException::class)
    fun handleIllegalStateException(ex: EmployeeServiceException): ResponseEntity<EmployeeServiceErrorResponse> {
        val (level, httpStatus, message) =
            when (ex) {
                is EmployeeServiceException.EmployeeDataSourceException ->
                    Triple(
                        Level.ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "databaseで予期しない例外が発生しました",
                    )

                is EmployeeServiceException.EmployeeUpdateNotApplicableException ->
                    Triple(
                        Level.WARN,
                        HttpStatus.GONE,
                        "社員更新中に対象の番号の社員が該当しませんでした",
                    )
            }
        logger.atLevel(level).log(message, ex)
        val errorResponse = EmployeeServiceErrorResponse(message)
        return ResponseEntity(errorResponse, httpStatus)
    }
}

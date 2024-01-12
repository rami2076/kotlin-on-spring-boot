package com.example.kotlinonspringboot.domain.model

sealed class EmployeeServiceException(override val cause: Throwable) : RuntimeException(cause) {
    class EmployeeDataSourceException(override val cause: Throwable) : EmployeeServiceException(cause)

    class EmployeeUpdateNotApplicableException(override val cause: Throwable) : EmployeeServiceException(cause)
}

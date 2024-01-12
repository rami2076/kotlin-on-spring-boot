package com.example.kotlinonspringboot.infrastructure.employee.repository

import com.example.kotlinonspringboot.domain.model.Employee
import com.example.kotlinonspringboot.domain.model.EmployeeNumber
import com.example.kotlinonspringboot.domain.model.EmployeeServiceException
import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition
import com.example.kotlinonspringboot.domain.model.condition.SearchCondition
import com.example.kotlinonspringboot.domain.repository.EmployeeRepository
import com.example.kotlinonspringboot.infrastructure.employee.datasource.mapper.EmployeeMapper
import org.springframework.stereotype.Repository

@Repository
class EmployeeRepositoryImpl(private val employeeMapper: EmployeeMapper) : EmployeeRepository {
    /**
     * 社員登録
     *
     * @param unRegisteredEmployee 未登録社員
     * @return insertが0件の場合はfalse
     */
    override fun save(unRegisteredEmployee: Employee.UnRegisteredEmployee): Boolean {
        try {
            val (fullName, age, emailAddress) = unRegisteredEmployee

            val insertedCount = employeeMapper.insert(fullName, age, emailAddress)

            return 0 != insertedCount
        } catch (ex: Exception) {
            throw EmployeeServiceException.EmployeeDataSourceException(ex)
        }
    }

    /**
     * 社員更新
     *
     * @param willRenewEmployee 更新予定社員
     * @return updateが0件の場合はEmployeeUpdateNotApplicableException
     */
    override fun update(willRenewEmployee: Employee.WillRenewEmployee): Boolean {
        try {
            val (employeeNumber, fullName, age, emailAddress) = willRenewEmployee

            val updatedCount = employeeMapper.update(employeeNumber.value, fullName, age, emailAddress)

            if (0 != updatedCount) {
                return true
            }
            throw EmployeeServiceException.EmployeeUpdateNotApplicableException(
                RuntimeException("更新対象の社員の番号が存在しませんでした。employeeNumber:${employeeNumber.value}"),
            )
        } catch (ex: Exception) {
            throw EmployeeServiceException.EmployeeDataSourceException(ex)
        }
    }

    /**
     * キーの昇順でソートした登録済みの全社員を取得
     *
     *@return 検索結果が0件の場合はEmptyList、それ以外は通常のList
     */
    override fun findAllSortedByKey(): List<Employee.RegisteredEmployee> {
        try {
            val employeeEntities = employeeMapper.findAllSortedListByKeyAsc()

            return employeeEntities.map { (employeeNumber, fullName, age, emailAddress) ->
                Employee.RegisteredEmployee(
                    employeeNumber = EmployeeNumber(employeeNumber),
                    fullName = fullName,
                    age = age,
                    emailAddress = emailAddress,
                )
            }
        } catch (ex: Exception) {
            throw EmployeeServiceException.EmployeeDataSourceException(ex)
        }
    }

    /**
     * PK社員検索
     *
     * @param searchPKSearchCondition 社員PK検索条件
     * @return 検索結果が0の場合はnullを返却
     */
    override fun findByKey(searchPKSearchCondition: SearchCondition.EmployeePKSearchCondition): Employee.RegisteredEmployee? {
        try {
            val employeeEntity = employeeMapper.findByKey(searchPKSearchCondition.employeeNumber.value)

            return when (employeeEntity) {
                null -> null
                else ->
                    employeeEntity.let {
                        Employee.RegisteredEmployee(
                            employeeNumber = EmployeeNumber(it.employeeNumber),
                            fullName = it.fullName,
                            age = it.age,
                            emailAddress = it.emailAddress,
                        )
                    }
            }
        } catch (ex: Exception) {
            throw EmployeeServiceException.EmployeeDataSourceException(ex)
        }
    }

    /**
     * 社員削除
     *
     * @param deleteCondition 社員PK削除条件
     * @return deleteが0件の場合はfalse
     */
    override fun deleteByKey(deleteCondition: DeleteCondition): Boolean {
        try {
            val deletedCount = employeeMapper.delete(deleteCondition.employeeNumber.value)

            return 0 != deletedCount
        } catch (ex: Exception) {
            throw EmployeeServiceException.EmployeeDataSourceException(ex)
        }
    }
}

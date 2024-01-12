package com.example.kotlinonspringboot.infrastructure.employee.datasource.mapper

import com.example.kotlinonspringboot.infrastructure.employee.datasource.entity.EmployeeEntity
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

@Mapper
interface EmployeeMapper {
    @Select("SELECT EMPLOYEE_NUMBER, FULL_NAME,AGE,EMAIL_ADDRESS FROM EMPLOYEE ORDER BY EMPLOYEE_NUMBER")
    fun findAllSortedListByKeyAsc(): List<EmployeeEntity>

    @Select(
        """
        SELECT EMPLOYEE_NUMBER, FULL_NAME,AGE,EMAIL_ADDRESS FROM EMPLOYEE
         WHERE EMPLOYEE_NUMBER = #{employeeNumber}""",
    )
    fun findByKey(employeeNumber: Long): EmployeeEntity?

    @Insert("INSERT INTO EMPLOYEE ( FULL_NAME,AGE,EMAIL_ADDRESS) value(#{fullName},#{age},#{emailAddress})")
    fun insert(
        fullName: String,
        age: Short,
        emailAddress: String,
    ): Int

    @Update(
        """
        UPDATE EMPLOYEE SET FULL_NAME= #{fullName}, AGE= #{age}, EMAIL_ADDRESS = #{emailAddress}
         WHERE EMPLOYEE_NUMBER = #{employeeNumber}""",
    )
    fun update(
        employeeNumber: Long,
        fullName: String,
        age: Short,
        emailAddress: String,
    ): Int

    @Delete(
        """
        DELETE FROM EMPLOYEE WHERE EMPLOYEE_NUMBER = #{employeeNumber}""",
    )
    fun delete(employeeNumber: Long): Int
}

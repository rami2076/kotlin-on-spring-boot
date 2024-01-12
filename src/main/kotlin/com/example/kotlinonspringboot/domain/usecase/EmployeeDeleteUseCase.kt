package com.example.kotlinonspringboot.domain.usecase

import com.example.kotlinonspringboot.domain.model.condition.DeleteCondition

interface EmployeeDeleteUseCase {
    /**
     * 削除ユースケース
     * @param deleteCondition 削除条件
     * @return true,削除した場合。false,削除対象がなかった場合。
     */
    fun delete(deleteCondition: DeleteCondition): Boolean
}

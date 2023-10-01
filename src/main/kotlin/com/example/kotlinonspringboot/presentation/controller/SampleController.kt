package com.example.kotlinonspringboot.presentation.controller

import com.example.kotlinonspringboot.presentation.api.CustomerApi
import com.example.kotlinonspringboot.presentation.model.Customer
import com.example.kotlinonspringboot.presentation.model.MultipleCustomersResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController : CustomerApi {
    override fun list(): ResponseEntity<MultipleCustomersResponse> {
        val customers = listOf(Customer("na", "na"))
        val response = MultipleCustomersResponse(customers)

        return ResponseEntity(response, HttpStatus.OK)
    }
}

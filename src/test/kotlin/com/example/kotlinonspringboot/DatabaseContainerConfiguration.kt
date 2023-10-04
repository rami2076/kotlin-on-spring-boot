package com.example.kotlinonspringboot

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

// ref: https://dev.to/j_a_o_v_c_t_r/testcontainers-with-mysql-and-redis-with-an-spring-boot-kotlin-application-4bmf
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class DatabaseContainerConfiguration {

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("mydatabase")
            withUsername("myuser")
            withPassword("secret")
            withInitScript("datasource/init_ddl.sql")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }
    }
}

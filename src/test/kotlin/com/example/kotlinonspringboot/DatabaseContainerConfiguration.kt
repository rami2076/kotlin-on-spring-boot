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
        private val mysqlContainer =
            MySQLContainer<Nothing>("mysql:latest").apply {
                withDatabaseName("mydatabase")
                withUsername("myuser")
                withPassword("secret")
                withInitScript("datasource/init_ddl.sql")
                // コンテナの再利用の設定としてwithReuse(true)+$HOME/.testcontainers.propertiesを作成したが、
                // 複数クラスでコンテナを利用する場合 Failed to validate connectionのメッセージが出力され状況が改善しないので
                // テストコンテナを使うテストクラスは一つにすることで問題を回避
                // 以下で対応できそうではある
                // https://danielme.com/2023/04/13/testing-spring-boot-docker-with-testcontainers-and-junit-5-mysql-and-other-images/
            }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.run {
                add("spring.datasource.url", mysqlContainer::getJdbcUrl)
                add("spring.datasource.password", mysqlContainer::getPassword)
                add("spring.datasource.username", mysqlContainer::getUsername)
            }
        }
    }
}

package com.speedit.api

import com.speedit.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpeeditApplication

fun main(args: Array<String>) {
    runApplication<SpeeditApplication>(*args)
}

package com.speedit.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpeeditApplication

fun main(args: Array<String>) {
    runApplication<SpeeditApplication>(*args)
}

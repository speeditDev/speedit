package com.speedit.server.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller("/")
class HomeController {
    @GetMapping("/ping")
    fun ping(): ResponseEntity<String> {
        return ResponseEntity.ok("pong")
    }

    @GetMapping("/health")
    fun checkHealth(): ResponseEntity<String> {
        return ResponseEntity.ok("ok")
    }
}
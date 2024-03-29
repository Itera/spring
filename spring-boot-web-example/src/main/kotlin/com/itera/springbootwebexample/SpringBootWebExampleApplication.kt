package com.itera.springbootwebexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWebExampleApplication

fun main(args: Array<String>) {
    @Suppress("SpreadOperator")
    runApplication<SpringBootWebExampleApplication>(*args)
}

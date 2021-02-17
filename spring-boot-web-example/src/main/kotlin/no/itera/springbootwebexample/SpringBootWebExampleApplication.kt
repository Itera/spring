package no.itera.springbootwebexample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootWebExampleApplication

fun main(args: Array<String>) {
	runApplication<SpringBootWebExampleApplication>(*args)
}

package com.gsc.order

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.gsc.order.*"])
class OrderApplication

fun main(args: Array<String>) {
	runApplication<OrderApplication>(*args)

	@Bean
	fun createModel() = ModelMapper()
}

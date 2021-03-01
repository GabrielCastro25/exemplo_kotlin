package com.gsc.stock

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = ["com.gsc.stock.*"])
class StockApplication

fun main(args: Array<String>) {
	runApplication<StockApplication>(*args)
}

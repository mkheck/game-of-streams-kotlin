package com.thehecklers.ksource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Supplier
import kotlin.random.Random

@SpringBootApplication
class KsourceApplication

fun main(args: Array<String>) {
	runApplication<KsourceApplication>(*args)
}

@Configuration
class CoffeeGrower(private val generator: CoffeeGenerator) {
	@Bean
	fun sendCoffee(): Supplier<WholesaleCoffee> = Supplier {
		generator.generate()
	}
}

@Component
class CoffeeGenerator() {
	val names = listOf("Kaldi's", "Espresso Roast", "Café Cereza", "Tim Hortons")
	val rnd = Random

	fun generate() = WholesaleCoffee(UUID.randomUUID().toString(),
		names[rnd.nextInt(names.size)])
}

data class WholesaleCoffee(val id: String, val name: String)
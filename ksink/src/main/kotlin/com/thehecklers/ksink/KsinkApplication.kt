package com.thehecklers.ksink

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@SpringBootApplication
class KsinkApplication

fun main(args: Array<String>) {
	runApplication<KsinkApplication>(*args)
}

@Configuration
class CoffeeDrinker {
	@Bean
	fun drinkIt(): Consumer<RetailCoffee> = Consumer {
		println(it)
	}
}

data class RetailCoffee(val id: String, val name: String, val state: State) {
	enum class State {
		WHOLE_BEAN,
		GROUND
	}
}
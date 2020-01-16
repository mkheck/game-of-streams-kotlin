package com.thehecklers.kprocessor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function
import kotlin.random.Random

@SpringBootApplication
class KprocessorApplication

fun main(args: Array<String>) {
    runApplication<KprocessorApplication>(*args)
}

@Configuration
class CoffeeRoaster {
    val rnd = Random

    @Bean
    fun processIt(): Function<WholesaleCoffee, RetailCoffee> = Function {
        val rCoffee = RetailCoffee(
            it.id,
            it.name,
            if (rnd.nextInt(2) == 0)
                RetailCoffee.State.WHOLE_BEAN
            else
                RetailCoffee.State.GROUND
        )

		println(rCoffee)

		rCoffee
	}

    //@Bean
    fun fixIt(): Function<RetailCoffee, RetailCoffee> = Function {
        val outCoffee = RetailCoffee(it.id,
            it.name,
            RetailCoffee.State.WHOLE_BEAN)

        println("   >> Was ${it.state}, now WHOLE_BEAN!")

        outCoffee
    }
}

data class RetailCoffee(val id: String, val name: String, val state: State) {
    enum class State {
        WHOLE_BEAN,
        GROUND
    }
}

data class WholesaleCoffee(val id: String, val name: String)
package chapter05

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val channels = Channel<Int>()

        launch {
            for( x in 1..5) {
                channels.send( x * x)
            }
            channels.close()
        }

            channels.consumeEach {
                delay(100L)
                println(it)
            }

        println("Done ")
    }
}
package chapter02

import java.util.concurrent.Executors

// AtomicCounter 를 사용한 예제
/*
20000
 */
fun main() {

    val counter = AtomicCounter()

    val task = Runnable {
        for (i in 1..10000) {
            counter.increment()
        }
    }

    val executorService = Executors.newCachedThreadPool()

    val future1 = executorService.submit(task, true)
    val future2 = executorService.submit(task, true)

    val isEnd = future1.get() && future2.get()

    if (isEnd) {
        println(counter.get())
    } else {
        println("실패")
    }

    executorService.shutdown()
}
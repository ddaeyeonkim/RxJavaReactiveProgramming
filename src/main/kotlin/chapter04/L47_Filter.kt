package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .filter { data -> data.toInt() % 2 == 0 }
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

fun main() {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3)
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
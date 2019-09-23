package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 44:35.075 ->  true
 */
fun main(args: Array<String>) {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3)
        .filter { data -> data >= 3 }
        .isEmpty
        .subscribe(DebugSingleObserver())

    Thread.sleep(4000L)
}
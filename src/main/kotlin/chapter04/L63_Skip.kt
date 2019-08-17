package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 28:59.672 ->  2
RxComputationThreadPool-1 || 28:59.697 ->  3
RxComputationThreadPool-1 || 28:59.797 ->  4
RxComputationThreadPool-1 || 28:59.893 ->  5
RxComputationThreadPool-1 || 28:59.994 ->  6
RxComputationThreadPool-1 || 28:59.994 -> 완료
 */
fun main() {
    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .skip(2)
        .take(5)
        .subscribe(DebugSubscriber())

    Thread.sleep(1000L)
}
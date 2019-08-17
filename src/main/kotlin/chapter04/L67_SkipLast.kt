package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 42:35.035 ->  0
RxComputationThreadPool-1 || 42:35.970 ->  1
RxComputationThreadPool-1 || 42:36.970 ->  2
RxComputationThreadPool-1 || 42:36.971 -> 완료
 */
fun main() {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(5)
        .skipLast(2)
        .subscribe(DebugSubscriber())

    Thread.sleep(6000L)
}
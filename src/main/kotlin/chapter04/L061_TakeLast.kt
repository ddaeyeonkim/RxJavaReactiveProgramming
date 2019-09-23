package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 23:26.638 ->  3
RxComputationThreadPool-1 || 23:26.640 ->  4
RxComputationThreadPool-1 || 23:26.640 -> 완료
 */
fun main() {
    Flowable.interval(800L, TimeUnit.MILLISECONDS)
        .take(5)
        .takeLast(2)
        .subscribe(DebugSubscriber())

    Thread.sleep(5000L)
}
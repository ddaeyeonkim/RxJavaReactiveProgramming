package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 47:10.774 ->  0
RxComputationThreadPool-2 || 47:10.905 ->  100
RxComputationThreadPool-1 || 47:11.002 ->  1
RxComputationThreadPool-1 || 47:11.302 ->  2
RxComputationThreadPool-2 || 47:11.405 ->  101
RxComputationThreadPool-1 || 47:11.604 ->  3
RxComputationThreadPool-1 || 47:11.904 ->  4
RxComputationThreadPool-1 || 47:11.906 -> 완료
 */
fun main() {
    Flowable.merge(
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(5),
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            .take(2)
            .map { data -> data + 100L }
    )
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
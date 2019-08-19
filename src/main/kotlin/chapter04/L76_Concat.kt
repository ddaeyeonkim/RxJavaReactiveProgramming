package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 43:21.799 ->  0
RxComputationThreadPool-1 || 43:22.029 ->  1
RxComputationThreadPool-1 || 43:22.330 ->  2
RxComputationThreadPool-1 || 43:22.627 ->  3
RxComputationThreadPool-1 || 43:22.928 ->  4
--------
RxComputationThreadPool-2 || 43:23.436 ->  100
RxComputationThreadPool-2 || 43:23.933 ->  101
RxComputationThreadPool-2 || 43:23.933 -> 완료
 */
fun main() {

    Flowable.concat(
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(5),
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            .take(2)
            .map { data -> data + 100L }
    )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
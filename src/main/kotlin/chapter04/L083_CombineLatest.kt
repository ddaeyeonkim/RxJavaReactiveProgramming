package chapter04

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-2 || 37:47.888 ->  [0, 100]
RxComputationThreadPool-1 || 37:47.893 ->  [1, 100]
RxComputationThreadPool-1 || 37:48.192 ->  [2, 100]
RxComputationThreadPool-2 || 37:48.294 ->  [2, 101]
RxComputationThreadPool-1 || 37:48.491 ->  [3, 101]
RxComputationThreadPool-1 || 37:48.791 ->  [4, 101]
RxComputationThreadPool-2 || 37:48.793 ->  [4, 102]
RxComputationThreadPool-2 || 37:48.798 -> 완료
 */
fun main(args: Array<String>) {
    Flowable.combineLatest(
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(5),
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            .take(3)
            .map { data -> data + 100L },
        BiFunction <Long, Long, List<Long>> {data1, data2 -> listOf(data1, data2) }
    )
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
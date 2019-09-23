package chapter04

import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-2 || 27:14.402 ->  [0, 50]
RxComputationThreadPool-2 || 27:14.814 ->  [1, 51]
RxComputationThreadPool-1 || 27:15.316 ->  [2, 52]
RxComputationThreadPool-2 || 27:15.814 ->  [3, 53]
RxComputationThreadPool-2 || 27:15.814 -> 완료
 */
fun main() {

    Flowable.zip(
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            .take(6),
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            .take(4)
            .map { data -> data + 50L },
        BiFunction<Long, Long, List<Long>> { t1, t2 -> listOf(t1, t2) }
    )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)

}
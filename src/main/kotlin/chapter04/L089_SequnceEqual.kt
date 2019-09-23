package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 56:50.609 ->  true
 */
fun main(args: Array<String>) {

    Flowable.sequenceEqual(
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
            .take(3),
        Flowable.just(0L, 1L, 3L)
    )
        .subscribe(DebugSingleObserver())
}
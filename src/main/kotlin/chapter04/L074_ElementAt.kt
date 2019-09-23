package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 15:48.504 ->  1
 */
fun main() {
    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .elementAt(1, 3)
        .subscribe(DebugSingleObserver())

    Thread.sleep(1000L)
}
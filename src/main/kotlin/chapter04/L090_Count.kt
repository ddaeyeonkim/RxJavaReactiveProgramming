package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 58:44.958 ->  3
 */
fun main(args: Array<String>) {

    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3)
        .count()
        .subscribe(DebugSingleObserver())

    Thread.sleep(4000L)
}
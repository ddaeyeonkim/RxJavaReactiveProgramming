package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 53:57.825 ->  true
 */
fun main(args: Array<String>) {

    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .take(3)
        .all { data -> data < 5 }
        .subscribe(DebugSingleObserver())

    Thread.sleep(4000L)
}
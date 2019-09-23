package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 16:34.655 ->  0
RxComputationThreadPool-1 || 16:34.684 ->  1
RxComputationThreadPool-1 || 16:34.784 ->  2
called
RxComputationThreadPool-2 || 16:34.886 ->  0
RxComputationThreadPool-2 || 16:34.985 ->  1
RxComputationThreadPool-2 || 16:35.085 ->  2
called
RxComputationThreadPool-2 || 16:35.085 -> 완료
 */
fun main(args: Array<String>) {
    val startTime = System.currentTimeMillis()

    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .take(3)
        .repeatUntil {
            println("called")
            System.currentTimeMillis() - startTime > 500L
        }
        .subscribe(DebugSubscriber())

    Thread.sleep(1000L)
}
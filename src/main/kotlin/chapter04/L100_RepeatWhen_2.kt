package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 48:41.582 ->  time : 48:41.580 || data : 0
RxComputationThreadPool-1 || 48:41.583 ->  time : 48:41.583 || data : 1
RxComputationThreadPool-1 || 48:41.676 ->  time : 48:41.676 || data : 2
emit : 0
RxComputationThreadPool-3 || 48:42.784 ->  time : 48:42.784 || data : 0
RxComputationThreadPool-3 || 48:42.882 ->  time : 48:42.882 || data : 1
RxComputationThreadPool-3 || 48:42.982 ->  time : 48:42.982 || data : 2
emit : 0
complete
RxComputationThreadPool-2 || 48:43.984 -> 완료
 */
fun main(args: Array<String>) {

    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .take(3)
        .repeatWhen { completeHandler ->
            completeHandler
                .delay(1000L, TimeUnit.MILLISECONDS)
                .take(2)
                .doOnNext { data -> println("emit : $data") }
                .doOnComplete { println("complete") }
        }
        .map { data -> "time : ${getCurrentTime()} || data : $data" }
        .subscribe(DebugSubscriber())

    Thread.sleep(5000L)
}
package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
main || 31:23.591 ->  time : 31:23.589 || data : 1
main || 31:23.591 ->  time : 31:23.591 || data : 2
main || 31:23.591 ->  time : 31:23.591 || data : 3
emit : 0
RxComputationThreadPool-1 || 31:24.595 ->  time : 31:24.595 || data : 1
RxComputationThreadPool-1 || 31:24.595 ->  time : 31:24.595 || data : 2
RxComputationThreadPool-1 || 31:24.595 ->  time : 31:24.595 || data : 3
emit : 0
RxComputationThreadPool-1 || 31:25.598 ->  time : 31:25.598 || data : 1
RxComputationThreadPool-1 || 31:25.598 ->  time : 31:25.598 || data : 2
RxComputationThreadPool-1 || 31:25.598 ->  time : 31:25.598 || data : 3
complete
RxComputationThreadPool-1 || 31:25.599 -> 완료
 */
fun main(args: Array<String>) {

    Flowable.just(1, 2, 3)
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
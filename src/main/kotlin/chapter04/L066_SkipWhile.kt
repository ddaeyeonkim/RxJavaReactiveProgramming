package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 40:05.142 ->  3
RxComputationThreadPool-1 || 40:05.379 ->  4
RxComputationThreadPool-1 || 40:05.680 ->  5
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        // false 를 반환한 시점부터 데이터 통지
        .skipWhile { data -> data != 3L }
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 34:57.778 ->  [0, 1, 2]
RxComputationThreadPool-1 || 34:58.002 ->  [3, 4, 5]
RxComputationThreadPool-1 || 34:58.304 ->  [6, 7, 8]
RxComputationThreadPool-1 || 34:58.403 ->  [9]
RxComputationThreadPool-1 || 34:58.403 -> 완료
 */
fun main() {
    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        .take(10)
        // 통지할 데이터를 지정한 범위까지 모아 리스트나 컬렉션으로 통지
        .buffer(3)
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
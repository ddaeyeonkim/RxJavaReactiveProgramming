package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 13:10.828 ->  2
RxComputationThreadPool-1 || 13:11.769 ->  5
RxComputationThreadPool-1 || 13:12.768 ->  8
RxComputationThreadPool-1 || 13:12.771 -> 완료
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(10)
        // 지정시간의 마지막 데이터 통지
        .throttleLast(1000L, TimeUnit.MILLISECONDS)
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
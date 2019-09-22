package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 31:17.982 -> 0
RxComputationThreadPool-2 || 31:18.120 ->  3
RxComputationThreadPool-2 || 31:18.415 ->  4
RxComputationThreadPool-2 || 31:18.719 ->  5
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        // 인자 flowable 이 데이터를 통지하면 그때 부터 원본 결과 데이터 통지
        .skipUntil(
            Flowable.timer(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(::printDataWithThreadNameAndTime)
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
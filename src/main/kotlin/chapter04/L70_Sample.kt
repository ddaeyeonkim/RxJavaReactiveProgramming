package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
sample
RxComputationThreadPool-1 || 18:29.658 -> 0
RxComputationThreadPool-1 || 18:29.660 ->  2
sample
RxComputationThreadPool-1 || 18:30.591 -> 1
RxComputationThreadPool-1 || 18:30.592 ->  5
RxComputationThreadPool-2 || 18:31.294 -> 완료
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(9)
        // 인자 flowable 의 통지 후, 원본의 마지막 데이터를 통지
        .sample(
            Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .doOnNext {
                    println("sample")
                    printDataWithThreadNameAndTime(it)
                }
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
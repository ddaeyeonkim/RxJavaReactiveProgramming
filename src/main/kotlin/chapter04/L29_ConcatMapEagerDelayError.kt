package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 27:55.590 ->  [10] 0
RxComputationThreadPool-1 || 27:56.021 ->  [10] 1
RxComputationThreadPool-1 || 27:56.520 ->  [10] 2
RxComputationThreadPool-1 || 27:56.520 ->  [11] 0
RxComputationThreadPool-1 || 27:56.520 ->  [12] 0
RxComputationThreadPool-1 || 27:56.520 ->  [12] 1
RxComputationThreadPool-3 || 27:56.521 ->  [12] 2
RxComputationThreadPool-3 || 27:56.521 -> 에러 : java.lang.Exception: 예외 발생
 */
fun main() {
    Flowable.range(10, 3)
        .concatMapEagerDelayError(
            { sourceData ->
                Flowable.interval(500L, TimeUnit.MILLISECONDS)
                    .take(3)
                    .doOnNext { data ->
                        if (sourceData == 11 && data.toInt() == 1) {
                            throw Exception("예외 발생")
                        }
                    }
                    .map { data -> "[$sourceData] $data" }
            },
            // 에러 발생시,
            // true 면 모든 데이터를 통지하고 에러 통지,
            // false 면 에러 발생 전까지 생성된 flowable 의 데이터를 통지 후 에러 통지
            true
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 05:01.395 ->  100
RxComputationThreadPool-1 || 05:01.819 ->  101
RxComputationThreadPool-2 || 05:02.124 ->  0
RxComputationThreadPool-2 || 05:02.424 ->  1
RxComputationThreadPool-2 || 05:02.720 ->  2
RxComputationThreadPool-2 || 05:03.021 ->  3
RxComputationThreadPool-2 || 05:03.320 ->  4
RxComputationThreadPool-2 || 05:03.320 -> 완료
 */
fun main() {
    // 부모 실행
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(5)
        .startWith(
            // 인자 먼저 실행
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map { data -> data + 100L }
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
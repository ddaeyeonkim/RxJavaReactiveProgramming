package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 08:30.160 ->  0
RxComputationThreadPool-1 || 08:31.280 ->  4
RxComputationThreadPool-1 || 08:32.479 ->  8
RxComputationThreadPool-1 || 08:32.779 -> 완료
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(10)
        // 데이터를 통지한 후 지정시간 동안 데이터를 통지하지 않는다
        .throttleFirst(1000L, TimeUnit.MILLISECONDS)
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
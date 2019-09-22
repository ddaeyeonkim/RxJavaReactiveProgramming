package chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 08:12.453 ->  A
RxComputationThreadPool-1 || 08:13.994 ->  D
main || 08:14.598 ->  E
main || 08:14.598 -> 완료
 */
fun main() {
    Flowable.create<String>(
        { emitter ->
            emitter.onNext("A")
            Thread.sleep(1000L)

            emitter.onNext("B")
            Thread.sleep(300L)

            emitter.onNext("C")
            Thread.sleep(300L)

            emitter.onNext("D")
            Thread.sleep(1000L)

            emitter.onNext("E")
            Thread.sleep(100L)

            emitter.onComplete()
        },
        BackpressureStrategy.BUFFER
    )
        // 지정한 기간 안에 다음 데이터가 오지 않으면 데이터를 통지
        .throttleWithTimeout(500L, TimeUnit.MILLISECONDS)
        .subscribe(DebugSubscriber())
}
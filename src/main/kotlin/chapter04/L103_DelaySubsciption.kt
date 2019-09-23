package chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
58:24.944
58:27.028
통지 시각 : 58:27.028
RxComputationThreadPool-1 || 58:27.029 ->  A
통지 시각 : 58:27.029
RxComputationThreadPool-1 || 58:27.029 ->  B
통지 시각 : 58:27.029
RxComputationThreadPool-1 || 58:27.029 ->  C
58:27.029
RxComputationThreadPool-1 || 58:27.029 -> 완료
 */
fun main() {
    println(getCurrentTime())

    Flowable.create<String>(
        { emitter ->
            println(getCurrentTime())
            emitter.onNext("A")
            emitter.onNext("B")
            emitter.onNext("C")
            println(getCurrentTime())
            emitter.onComplete()
        },
        BackpressureStrategy.BUFFER
    )
        // 데이터 생성 전에 처리를 늦춤
        .delaySubscription(2000L, TimeUnit.MILLISECONDS)
        .doOnNext { println("통지 시각 : ${getCurrentTime()}") }
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
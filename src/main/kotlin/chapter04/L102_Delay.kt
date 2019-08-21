package chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
52:52.437
52:52.510
통지 시각 : 52:54.512
RxComputationThreadPool-1 || 52:54.512 ->  A
통지 시각 : 52:54.512
RxComputationThreadPool-1 || 52:54.513 ->  B
통지 시각 : 52:54.513
RxComputationThreadPool-1 || 52:54.513 ->  C
RxComputationThreadPool-1 || 52:54.513 -> 완료
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
        // 데이터를 생성한 후에 통지를 늦춤
        .delay(2000L, TimeUnit.MILLISECONDS)
        .doOnNext { println("통지 시각 : ${getCurrentTime()}") }
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
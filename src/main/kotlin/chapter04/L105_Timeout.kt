package chapter04

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
main || 15:36.629 ->  1
main || 15:36.630 ->  2
RxComputationThreadPool-1 || 15:37.634 -> 에러 : java.util.concurrent.TimeoutException: The source did not signal an event for 1000 milliseconds and has been terminated.
 */
fun main() {
    Flowable.create<Int>(
        { emitter ->
            emitter.onNext(1)
            emitter.onNext(2)

            try {
                Thread.sleep(1200L)
            } catch (e: InterruptedException) {
                emitter.onError(e)
                return@create
            }

            emitter.onNext(3)
            emitter.onComplete()
        },
        BackpressureStrategy.BUFFER
    )
        .timeout(1000L, TimeUnit.MILLISECONDS)
        .subscribe(DebugSubscriber())
}
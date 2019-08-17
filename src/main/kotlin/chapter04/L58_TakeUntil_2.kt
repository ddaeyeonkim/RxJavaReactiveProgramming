package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-2 || 14:58.893 ->  0
RxComputationThreadPool-2 || 14:59.125 ->  1
RxComputationThreadPool-2 || 14:59.422 ->  2
RxComputationThreadPool-1 || 14:59.522 -> 0
RxComputationThreadPool-1 || 14:59.524 -> 완료
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .takeUntil(
            Flowable.timer(1000L, TimeUnit.MILLISECONDS)
                .doOnNext(::printDataWithThreadNameAndTime)
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(2000L)
}
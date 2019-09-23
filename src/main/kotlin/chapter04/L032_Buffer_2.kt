package chapter04

import io.reactivex.Flowable
import java.util.concurrent.Callable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 45:42.483 -> 0
RxComputationThreadPool-1 || 45:42.485 ->  [0, 1, 2]
RxComputationThreadPool-3 || 45:43.490 -> 0
RxComputationThreadPool-3 || 45:43.490 ->  [3, 4, 5]
RxComputationThreadPool-2 || 45:43.521 ->  [6]
RxComputationThreadPool-2 || 45:43.521 -> 완료
 */
fun main() {
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        .take(7)
        // 호출 되면 1000 밀리초 뒤에 데이터를 통지하는 flowable 생성
        .buffer(
            Callable {
                Flowable.timer(1000L, TimeUnit.MILLISECONDS)
                    .doOnNext { data -> printDataWithThreadNameAndTime(data) }
            }
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
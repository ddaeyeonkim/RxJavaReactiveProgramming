package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 49:47.484 ->  1565999387401 ms: [10] 0
RxComputationThreadPool-1 || 49:47.900 ->  1565999387900 ms: [10] 1
RxComputationThreadPool-2 || 49:48.404 ->  1565999388404 ms: [11] 0
RxComputationThreadPool-2 || 49:48.904 ->  1565999388904 ms: [11] 1
RxComputationThreadPool-3 || 49:49.409 ->  1565999389409 ms: [12] 0
RxComputationThreadPool-3 || 49:49.909 ->  1565999389908 ms: [12] 1
완료
 */
fun main() {
    Flowable.range(10, 3)
        // 원본데이터의 순서대로 통지
        .concatMap { sourceData ->
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map { data ->
                    "${System.currentTimeMillis()} ms: [$sourceData] $data"
                }
        }
        // 생성한 flowable 의 처리가 완료될때까지 에러 통지를 미룬다
//        .concatMapDelayError { sourceData -> }
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
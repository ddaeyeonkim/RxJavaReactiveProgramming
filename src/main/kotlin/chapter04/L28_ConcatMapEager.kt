package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 25:24.313 ->  1566001524182 ms [10] 0
RxComputationThreadPool-1 || 25:24.682 ->  1566001524682 ms [10] 1
RxComputationThreadPool-1 || 25:24.682 ->  1566001524181 ms [11] 0
RxComputationThreadPool-1 || 25:24.682 ->  1566001524682 ms [11] 1
RxComputationThreadPool-1 || 25:24.682 ->  1566001524181 ms [12] 0
RxComputationThreadPool-1 || 25:24.682 ->  1566001524682 ms [12] 1
완료
 */
fun main() {
    Flowable.range(10, 3)
        // 생성된 flowable 바로 실행, 결과는 버퍼에 원본의 순서대로 쌓아서 통지
        .concatMapEager { sourceData ->
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(2)
                .map { data ->
                    "${System.currentTimeMillis()} ms [$sourceData] $data"
                }
        }
        .subscribe(DebugSubscriber())

    Thread.sleep(4000L)
}
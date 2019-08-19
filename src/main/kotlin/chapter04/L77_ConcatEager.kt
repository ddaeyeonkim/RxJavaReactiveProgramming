package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 57:47.185 ->  0
RxComputationThreadPool-1 || 57:47.414 ->  1
RxComputationThreadPool-1 || 57:47.709 ->  2
RxComputationThreadPool-1 || 57:48.009 ->  3
RxComputationThreadPool-1 || 57:48.314 ->  4
// 한번에 받아온것을 확인 가능
RxComputationThreadPool-1 || 57:48.314 ->  100
RxComputationThreadPool-1 || 57:48.314 ->  101
RxComputationThreadPool-1 || 57:48.314 ->  102
//
RxComputationThreadPool-2 || 57:48.815 ->  103
RxComputationThreadPool-2 || 57:49.315 ->  104
RxComputationThreadPool-2 || 57:49.316 -> 완료
 */
fun main() {

    /**
     * 원래의 flowable 이 한꺼번에 실행되지만, 통지는 첫번째 flowable 의 데이터부터 되며
     * 이통 통지가 끝나면 다음 flowable 의 데이터가 통지된다.
     * 이전 flowable 의 완료 시점에 바로 전까지 캐시에쌓인 데이터를 통지한다.
     */
    Flowable.concatEager(
        // 한번 감싸야 한다
        listOf(
            Flowable.interval(300L, TimeUnit.MILLISECONDS)
                .take(5),
            Flowable.interval(500L, TimeUnit.MILLISECONDS)
                .take(5)
                .map { data -> data + 100L }
        )
    )
        .subscribe(DebugSubscriber())

    Thread.sleep(3000L)
}
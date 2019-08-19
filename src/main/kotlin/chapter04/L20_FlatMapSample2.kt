package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-3 || 25:28.093 ->  [ 3 ] 0
RxComputationThreadPool-3 || 25:28.094 ->  [ 1 ] 0
RxComputationThreadPool-3 || 25:28.094 ->  [ 2 ] 0
RxComputationThreadPool-2 || 25:28.114 ->  [ 2 ] 1
RxComputationThreadPool-2 || 25:28.114 ->  [ 3 ] 1
RxComputationThreadPool-1 || 25:28.115 ->  [ 1 ] 1
RxComputationThreadPool-2 || 25:28.214 ->  [ 2 ] 2
RxComputationThreadPool-2 || 25:28.214 ->  [ 1 ] 2
RxComputationThreadPool-2 || 25:28.214 ->  [ 3 ] 2
RxComputationThreadPool-1 || 25:28.314 ->  [ 1 ] 3
RxComputationThreadPool-1 || 25:28.314 ->  [ 2 ] 3
RxComputationThreadPool-3 || 25:28.316 ->  [ 3 ] 3
완료
 */
fun main(args: Array<String>) {

    Flowable.range(1, 3)
        .flatMap(
            // 첫번째 인자 : 데이터를 받으면 새로운 Flowable 을 생성
            { Flowable.interval(100L, TimeUnit.MILLISECONDS).take(4) },
            // 두번째 인자 : 원본 데이터와 변환한 데이터로 새로운 통지 데이터를 생성
            { sourceData, newData -> "[ $sourceData ] $newData" }
        )
        .subscribe(DebugSubscriber())

    Thread.sleep(1000L)
}
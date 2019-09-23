package chapter04

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

/*
RxComputationThreadPool-1 || 47:51.293 ->  true
 */
fun main(args: Array<String>) {
    /**
     * 지정한 데이터를 포함하는지 판단
     * Single 반환
     * 원본에서 데이터를 통지하거나 완료를 통지할 때
     */
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        .contains(3L)
        .subscribe(DebugSingleObserver())

    Thread.sleep(4000L)
}